package com.arogyasahaya.app.data.database;

import android.content.Context;
import androidx.room.*;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.arogyasahaya.app.data.dao.*;
import com.arogyasahaya.app.data.entity.*;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
    entities = {Medication.class, VitalLog.class, UserProfile.class, AshaEvent.class},
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public abstract MedicationDao medicationDao();
    public abstract VitalLogDao vitalLogDao();
    public abstract UserProfileDao userProfileDao();
    public abstract AshaEventDao ashaEventDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "arogya_sahaya_database"
                    )
                    .addCallback(sRoomDatabaseCallback)
                    .build();
                }
            }
        }
        return INSTANCE;
    }

    // Pre-populate with simulated ASHA data on first run
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                AshaEventDao ashaDao = INSTANCE.ashaEventDao();
                // Seed simulated health camp data
                if (ashaDao.getEventCount() == 0) {
                    Calendar cal = Calendar.getInstance();

                    cal.add(Calendar.DAY_OF_MONTH, 5);
                    cal.set(Calendar.HOUR_OF_DAY, 9);
                    cal.set(Calendar.MINUTE, 0);
                    ashaDao.insert(new AshaEvent(
                        "Free Blood Pressure Camp",
                        "Free BP screening and consultation for all age groups. Bring your medical records.",
                        "Primary Health Centre, Main Road",
                        cal.getTimeInMillis(),
                        "health_camp"
                    ));

                    cal.add(Calendar.DAY_OF_MONTH, 8);
                    ashaDao.insert(new AshaEvent(
                        "ASHA Worker Home Visit",
                        "Monthly wellness check-up by your ASHA worker. Have your vitals log ready.",
                        "Your Home",
                        cal.getTimeInMillis(),
                        "asha_visit"
                    ));

                    cal.add(Calendar.DAY_OF_MONTH, 12);
                    ashaDao.insert(new AshaEvent(
                        "Diabetes Awareness & Screening",
                        "Free blood glucose testing, diet counseling, and medicine distribution.",
                        "Village Panchayat Hall",
                        cal.getTimeInMillis(),
                        "health_camp"
                    ));

                    cal.add(Calendar.DAY_OF_MONTH, 20);
                    ashaDao.insert(new AshaEvent(
                        "Eye & General Health Camp",
                        "Free eye checkup, general health screening with specialist doctors.",
                        "Government School Ground",
                        cal.getTimeInMillis(),
                        "health_camp"
                    ));

                    cal.add(Calendar.DAY_OF_MONTH, 30);
                    ashaDao.insert(new AshaEvent(
                        "Vaccination Drive",
                        "Seasonal flu vaccination for elderly and children. Free of cost.",
                        "Sub-Health Centre",
                        cal.getTimeInMillis(),
                        "vaccination"
                    ));
                }
            });
        }
    };
}

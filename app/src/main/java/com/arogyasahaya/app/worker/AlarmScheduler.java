package com.arogyasahaya.app.worker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.arogyasahaya.app.data.database.AppDatabase;
import com.arogyasahaya.app.data.entity.Medication;
import java.util.Calendar;
import java.util.List;

/**
 * AlarmScheduler handles scheduling of exact alarms using AlarmManager.setExactAndAllowWhileIdle()
 * which fires even when the device is in Doze mode — satisfying the critical success criterion.
 */
public class AlarmScheduler {

    public static final int MORNING_HOUR = 8;
    public static final int MORNING_MINUTE = 0;
    public static final int AFTERNOON_HOUR = 14;
    public static final int AFTERNOON_MINUTE = 0;
    public static final int NIGHT_HOUR = 21;
    public static final int NIGHT_MINUTE = 0;

    public static void scheduleMedicationAlarm(Context context, Medication medication) {
        if (medication.morning) {
            scheduleAlarm(context, medication, MORNING_HOUR, MORNING_MINUTE, "Morning", 0);
        }
        if (medication.afternoon) {
            scheduleAlarm(context, medication, AFTERNOON_HOUR, AFTERNOON_MINUTE, "Afternoon", 1);
        }
        if (medication.night) {
            scheduleAlarm(context, medication, NIGHT_HOUR, NIGHT_MINUTE, "Night", 2);
        }
    }

    private static void scheduleAlarm(Context context, Medication medication,
                                       int hour, int minute, String timeSlot, int slotOffset) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) return;

        Intent intent = new Intent(context, MedicationAlarmReceiver.class);
        intent.setAction(MedicationAlarmReceiver.ACTION_MEDICATION_ALARM);
        intent.putExtra(MedicationAlarmReceiver.EXTRA_MEDICINE_NAME, medication.name);
        intent.putExtra(MedicationAlarmReceiver.EXTRA_MEDICINE_ID, medication.id);
        intent.putExtra(MedicationAlarmReceiver.EXTRA_TIME_SLOT, timeSlot);

        int requestCode = medication.id * 10 + slotOffset;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, requestCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // If the time has already passed today, schedule for tomorrow
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Use setExactAndAllowWhileIdle to work even in Doze mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    pendingIntent
            );
        } else {
            alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    pendingIntent
            );
        }
    }

    public static void cancelMedicationAlarm(Context context, Medication medication) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) return;

        for (int slotOffset = 0; slotOffset <= 2; slotOffset++) {
            Intent intent = new Intent(context, MedicationAlarmReceiver.class);
            intent.setAction(MedicationAlarmReceiver.ACTION_MEDICATION_ALARM);
            int requestCode = medication.id * 10 + slotOffset;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, requestCode, intent,
                    PendingIntent.FLAG_NO_CREATE | PendingIntent.FLAG_IMMUTABLE
            );
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent);
            }
        }
    }

    public static void rescheduleAllAlarms(Context context) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(context);
            List<Medication> medications = db.medicationDao().getAllActiveMedicationsSync();
            for (Medication med : medications) {
                scheduleMedicationAlarm(context, med);
            }
        });
    }
}

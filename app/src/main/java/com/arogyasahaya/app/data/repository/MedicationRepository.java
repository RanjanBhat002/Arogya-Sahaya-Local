package com.arogyasahaya.app.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.arogyasahaya.app.data.dao.MedicationDao;
import com.arogyasahaya.app.data.database.AppDatabase;
import com.arogyasahaya.app.data.entity.Medication;
import java.util.List;

public class MedicationRepository {

    private final MedicationDao medicationDao;
    private final LiveData<List<Medication>> allActiveMedications;

    public MedicationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        medicationDao = db.medicationDao();
        allActiveMedications = medicationDao.getAllActiveMedications();
    }

    public LiveData<List<Medication>> getAllActiveMedications() {
        return allActiveMedications;
    }

    public void insert(Medication medication, OnInsertCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long id = medicationDao.insert(medication);
            if (callback != null) callback.onInserted((int) id);
        });
    }

    public void update(Medication medication) {
        AppDatabase.databaseWriteExecutor.execute(() -> medicationDao.update(medication));
    }

    public void delete(Medication medication) {
        AppDatabase.databaseWriteExecutor.execute(() -> medicationDao.delete(medication));
    }

    public void softDelete(int id) {
        AppDatabase.databaseWriteExecutor.execute(() -> medicationDao.softDelete(id));
    }

    public void getAllActiveMedicationsSync(OnListCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<Medication> meds = medicationDao.getAllActiveMedicationsSync();
            if (callback != null) callback.onResult(meds);
        });
    }

    public interface OnInsertCallback {
        void onInserted(int id);
    }

    public interface OnListCallback {
        void onResult(List<Medication> medications);
    }
}

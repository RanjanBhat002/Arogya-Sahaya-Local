package com.arogyasahaya.app.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.arogyasahaya.app.data.dao.VitalLogDao;
import com.arogyasahaya.app.data.database.AppDatabase;
import com.arogyasahaya.app.data.entity.VitalLog;
import java.util.List;

public class VitalLogRepository {

    private final VitalLogDao vitalLogDao;
    private final LiveData<List<VitalLog>> last7DaysVitals;
    private final LiveData<VitalLog> latestVital;
    private final LiveData<List<VitalLog>> allVitalLogs;

    public VitalLogRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        vitalLogDao = db.vitalLogDao();
        last7DaysVitals = vitalLogDao.getLast7DaysVitals();
        latestVital = vitalLogDao.getLatestVital();
        allVitalLogs = vitalLogDao.getAllVitalLogs();
    }

    public LiveData<List<VitalLog>> getLast7DaysVitals() {
        return last7DaysVitals;
    }

    public LiveData<VitalLog> getLatestVital() {
        return latestVital;
    }

    public LiveData<List<VitalLog>> getAllVitalLogs() {
        return allVitalLogs;
    }

    public void insert(VitalLog vitalLog) {
        AppDatabase.databaseWriteExecutor.execute(() -> vitalLogDao.insert(vitalLog));
    }

    public void update(VitalLog vitalLog) {
        AppDatabase.databaseWriteExecutor.execute(() -> vitalLogDao.update(vitalLog));
    }

    public void delete(VitalLog vitalLog) {
        AppDatabase.databaseWriteExecutor.execute(() -> vitalLogDao.delete(vitalLog));
    }
}

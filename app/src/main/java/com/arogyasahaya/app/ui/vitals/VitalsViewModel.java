package com.arogyasahaya.app.ui.vitals;

import android.app.Application;
import androidx.lifecycle.*;
import com.arogyasahaya.app.data.entity.VitalLog;
import com.arogyasahaya.app.data.repository.VitalLogRepository;
import java.util.List;

public class VitalsViewModel extends AndroidViewModel {

    private final VitalLogRepository repository;
    private final LiveData<List<VitalLog>> last7DaysVitals;
    private final LiveData<VitalLog> latestVital;
    private final LiveData<List<VitalLog>> allVitals;

    public VitalsViewModel(Application application) {
        super(application);
        repository = new VitalLogRepository(application);
        last7DaysVitals = repository.getLast7DaysVitals();
        latestVital = repository.getLatestVital();
        allVitals = repository.getAllVitalLogs();
    }

    public LiveData<List<VitalLog>> getLast7DaysVitals() {
        return last7DaysVitals;
    }

    public LiveData<VitalLog> getLatestVital() {
        return latestVital;
    }

    public LiveData<List<VitalLog>> getAllVitals() {
        return allVitals;
    }

    public void insert(VitalLog vitalLog) {
        repository.insert(vitalLog);
    }

    public void delete(VitalLog vitalLog) {
        repository.delete(vitalLog);
    }
}

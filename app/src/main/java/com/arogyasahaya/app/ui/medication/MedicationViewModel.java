package com.arogyasahaya.app.ui.medication;

import android.app.Application;
import androidx.lifecycle.*;
import com.arogyasahaya.app.data.entity.Medication;
import com.arogyasahaya.app.data.repository.MedicationRepository;
import java.util.List;

public class MedicationViewModel extends AndroidViewModel {

    private final MedicationRepository repository;
    private final LiveData<List<Medication>> allMedications;

    public MedicationViewModel(Application application) {
        super(application);
        repository = new MedicationRepository(application);
        allMedications = repository.getAllActiveMedications();
    }

    public LiveData<List<Medication>> getAllMedications() {
        return allMedications;
    }

    public void insert(Medication medication, MedicationRepository.OnInsertCallback callback) {
        repository.insert(medication, callback);
    }

    public void update(Medication medication) {
        repository.update(medication);
    }

    public void delete(Medication medication) {
        repository.delete(medication);
    }

    public void softDelete(int id) {
        repository.softDelete(id);
    }
}

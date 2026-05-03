package com.arogyasahaya.app.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.arogyasahaya.app.data.entity.Medication;
import java.util.List;

@Dao
public interface MedicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Medication medication);

    @Update
    void update(Medication medication);

    @Delete
    void delete(Medication medication);

    @Query("SELECT * FROM medications WHERE is_active = 1 ORDER BY name ASC")
    LiveData<List<Medication>> getAllActiveMedications();

    @Query("SELECT * FROM medications WHERE is_active = 1 ORDER BY name ASC")
    List<Medication> getAllActiveMedicationsSync();

    @Query("SELECT * FROM medications WHERE id = :id")
    LiveData<Medication> getMedicationById(int id);

    @Query("SELECT * FROM medications WHERE morning = 1 AND is_active = 1")
    List<Medication> getMorningMedications();

    @Query("SELECT * FROM medications WHERE afternoon = 1 AND is_active = 1")
    List<Medication> getAfternoonMedications();

    @Query("SELECT * FROM medications WHERE night = 1 AND is_active = 1")
    List<Medication> getNightMedications();

    @Query("UPDATE medications SET is_active = 0 WHERE id = :id")
    void softDelete(int id);

    @Query("SELECT COUNT(*) FROM medications WHERE is_active = 1")
    int getActiveMedicationCount();
}

package com.arogyasahaya.app.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.arogyasahaya.app.data.entity.VitalLog;
import java.util.List;

@Dao
public interface VitalLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(VitalLog vitalLog);

    @Update
    void update(VitalLog vitalLog);

    @Delete
    void delete(VitalLog vitalLog);

    @Query("SELECT * FROM vital_logs ORDER BY recorded_at DESC")
    LiveData<List<VitalLog>> getAllVitalLogs();

    @Query("SELECT * FROM vital_logs ORDER BY recorded_at DESC LIMIT 7")
    LiveData<List<VitalLog>> getLast7DaysVitals();

    @Query("SELECT * FROM vital_logs ORDER BY recorded_at DESC LIMIT 7")
    List<VitalLog> getLast7DaysVitalsSync();

    @Query("SELECT * FROM vital_logs ORDER BY recorded_at DESC LIMIT 1")
    LiveData<VitalLog> getLatestVital();

    @Query("SELECT * FROM vital_logs WHERE recorded_at >= :fromTime ORDER BY recorded_at DESC")
    LiveData<List<VitalLog>> getVitalsSince(long fromTime);

    @Query("SELECT * FROM vital_logs ORDER BY recorded_at DESC LIMIT 30")
    LiveData<List<VitalLog>> getLast30DaysVitals();
}

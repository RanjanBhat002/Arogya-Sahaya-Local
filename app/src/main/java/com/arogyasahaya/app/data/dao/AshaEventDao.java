package com.arogyasahaya.app.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.arogyasahaya.app.data.entity.AshaEvent;
import java.util.List;

@Dao
public interface AshaEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(AshaEvent event);

    @Update
    void update(AshaEvent event);

    @Delete
    void delete(AshaEvent event);

    @Query("SELECT * FROM asha_events ORDER BY event_date ASC")
    LiveData<List<AshaEvent>> getAllEvents();

    @Query("SELECT * FROM asha_events WHERE event_date >= :now ORDER BY event_date ASC")
    LiveData<List<AshaEvent>> getUpcomingEvents(long now);

    @Query("SELECT * FROM asha_events WHERE event_date >= :now ORDER BY event_date ASC LIMIT 1")
    LiveData<AshaEvent> getNextEvent(long now);

    @Query("UPDATE asha_events SET is_registered = 1 WHERE id = :id")
    void registerForEvent(int id);

    @Query("SELECT COUNT(*) FROM asha_events")
    int getEventCount();
}

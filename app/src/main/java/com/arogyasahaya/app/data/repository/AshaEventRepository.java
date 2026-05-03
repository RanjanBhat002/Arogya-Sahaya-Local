package com.arogyasahaya.app.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.arogyasahaya.app.data.dao.AshaEventDao;
import com.arogyasahaya.app.data.database.AppDatabase;
import com.arogyasahaya.app.data.entity.AshaEvent;
import java.util.List;

public class AshaEventRepository {

    private final AshaEventDao ashaEventDao;

    public AshaEventRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        ashaEventDao = db.ashaEventDao();
    }

    public LiveData<List<AshaEvent>> getUpcomingEvents() {
        return ashaEventDao.getUpcomingEvents(System.currentTimeMillis());
    }

    public LiveData<AshaEvent> getNextEvent() {
        return ashaEventDao.getNextEvent(System.currentTimeMillis());
    }

    public LiveData<List<AshaEvent>> getAllEvents() {
        return ashaEventDao.getAllEvents();
    }

    public void insert(AshaEvent event) {
        AppDatabase.databaseWriteExecutor.execute(() -> ashaEventDao.insert(event));
    }

    public void registerForEvent(int id) {
        AppDatabase.databaseWriteExecutor.execute(() -> ashaEventDao.registerForEvent(id));
    }
}

package com.arogyasahaya.app.ui.asha;

import android.app.Application;
import androidx.lifecycle.*;
import com.arogyasahaya.app.data.entity.AshaEvent;
import com.arogyasahaya.app.data.repository.AshaEventRepository;
import java.util.List;

public class AshaViewModel extends AndroidViewModel {

    private final AshaEventRepository repository;
    private final LiveData<List<AshaEvent>> upcomingEvents;
    private final LiveData<AshaEvent> nextEvent;

    public AshaViewModel(Application application) {
        super(application);
        repository = new AshaEventRepository(application);
        upcomingEvents = repository.getUpcomingEvents();
        nextEvent = repository.getNextEvent();
    }

    public LiveData<List<AshaEvent>> getUpcomingEvents() {
        return upcomingEvents;
    }

    public LiveData<AshaEvent> getNextEvent() {
        return nextEvent;
    }

    public void registerForEvent(int id) {
        repository.registerForEvent(id);
    }
}

package com.arogyasahaya.app.ui.profile;

import android.app.Application;
import androidx.lifecycle.*;
import com.arogyasahaya.app.data.entity.UserProfile;
import com.arogyasahaya.app.data.repository.UserProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    private final UserProfileRepository repository;
    private final LiveData<UserProfile> profile;

    public ProfileViewModel(Application application) {
        super(application);
        repository = new UserProfileRepository(application);
        profile = repository.getProfile();
    }

    public LiveData<UserProfile> getProfile() {
        return profile;
    }

    public void saveProfile(UserProfile profile) {
        repository.saveProfile(profile);
    }
}

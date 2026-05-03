package com.arogyasahaya.app.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.arogyasahaya.app.data.dao.UserProfileDao;
import com.arogyasahaya.app.data.database.AppDatabase;
import com.arogyasahaya.app.data.entity.UserProfile;

public class UserProfileRepository {

    private final UserProfileDao userProfileDao;
    private final LiveData<UserProfile> profile;

    public UserProfileRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userProfileDao = db.userProfileDao();
        profile = userProfileDao.getProfile();
    }

    public LiveData<UserProfile> getProfile() {
        return profile;
    }

    public void saveProfile(UserProfile profile) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userProfileDao.insert(profile);
        });
    }
}

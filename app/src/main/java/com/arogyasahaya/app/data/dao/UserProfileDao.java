package com.arogyasahaya.app.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.arogyasahaya.app.data.entity.UserProfile;

@Dao
public interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserProfile profile);

    @Update
    void update(UserProfile profile);

    @Query("SELECT * FROM user_profile WHERE id = 1")
    LiveData<UserProfile> getProfile();

    @Query("SELECT * FROM user_profile WHERE id = 1")
    UserProfile getProfileSync();

    @Query("SELECT COUNT(*) FROM user_profile")
    int getProfileCount();
}

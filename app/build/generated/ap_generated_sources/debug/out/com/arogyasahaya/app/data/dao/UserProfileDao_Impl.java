package com.arogyasahaya.app.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.arogyasahaya.app.data.entity.UserProfile;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProfileDao_Impl implements UserProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProfile> __insertionAdapterOfUserProfile;

  private final EntityDeletionOrUpdateAdapter<UserProfile> __updateAdapterOfUserProfile;

  public UserProfileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProfile = new EntityInsertionAdapter<UserProfile>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_profile` (`id`,`name`,`age`,`gender`,`blood_group`,`chronic_conditions`,`allergies`,`emergency_contact_name`,`emergency_contact_phone`,`asha_worker_name`,`asha_worker_phone`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final UserProfile entity) {
        statement.bindLong(1, entity.id);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        statement.bindLong(3, entity.age);
        if (entity.gender == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.gender);
        }
        if (entity.bloodGroup == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.bloodGroup);
        }
        if (entity.chronicConditions == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.chronicConditions);
        }
        if (entity.allergies == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.allergies);
        }
        if (entity.emergencyContactName == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.emergencyContactName);
        }
        if (entity.emergencyContactPhone == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.emergencyContactPhone);
        }
        if (entity.ashaWorkerName == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.ashaWorkerName);
        }
        if (entity.ashaWorkerPhone == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.ashaWorkerPhone);
        }
      }
    };
    this.__updateAdapterOfUserProfile = new EntityDeletionOrUpdateAdapter<UserProfile>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_profile` SET `id` = ?,`name` = ?,`age` = ?,`gender` = ?,`blood_group` = ?,`chronic_conditions` = ?,`allergies` = ?,`emergency_contact_name` = ?,`emergency_contact_phone` = ?,`asha_worker_name` = ?,`asha_worker_phone` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final UserProfile entity) {
        statement.bindLong(1, entity.id);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        statement.bindLong(3, entity.age);
        if (entity.gender == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.gender);
        }
        if (entity.bloodGroup == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.bloodGroup);
        }
        if (entity.chronicConditions == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.chronicConditions);
        }
        if (entity.allergies == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.allergies);
        }
        if (entity.emergencyContactName == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.emergencyContactName);
        }
        if (entity.emergencyContactPhone == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.emergencyContactPhone);
        }
        if (entity.ashaWorkerName == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.ashaWorkerName);
        }
        if (entity.ashaWorkerPhone == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.ashaWorkerPhone);
        }
        statement.bindLong(12, entity.id);
      }
    };
  }

  @Override
  public void insert(final UserProfile profile) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserProfile.insert(profile);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final UserProfile profile) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUserProfile.handle(profile);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<UserProfile> getProfile() {
    final String _sql = "SELECT * FROM user_profile WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"user_profile"}, false, new Callable<UserProfile>() {
      @Override
      @Nullable
      public UserProfile call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfBloodGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_group");
          final int _cursorIndexOfChronicConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "chronic_conditions");
          final int _cursorIndexOfAllergies = CursorUtil.getColumnIndexOrThrow(_cursor, "allergies");
          final int _cursorIndexOfEmergencyContactName = CursorUtil.getColumnIndexOrThrow(_cursor, "emergency_contact_name");
          final int _cursorIndexOfEmergencyContactPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "emergency_contact_phone");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfAshaWorkerPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_phone");
          final UserProfile _result;
          if (_cursor.moveToFirst()) {
            _result = new UserProfile();
            _result.id = _cursor.getInt(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfName)) {
              _result.name = null;
            } else {
              _result.name = _cursor.getString(_cursorIndexOfName);
            }
            _result.age = _cursor.getInt(_cursorIndexOfAge);
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _result.gender = null;
            } else {
              _result.gender = _cursor.getString(_cursorIndexOfGender);
            }
            if (_cursor.isNull(_cursorIndexOfBloodGroup)) {
              _result.bloodGroup = null;
            } else {
              _result.bloodGroup = _cursor.getString(_cursorIndexOfBloodGroup);
            }
            if (_cursor.isNull(_cursorIndexOfChronicConditions)) {
              _result.chronicConditions = null;
            } else {
              _result.chronicConditions = _cursor.getString(_cursorIndexOfChronicConditions);
            }
            if (_cursor.isNull(_cursorIndexOfAllergies)) {
              _result.allergies = null;
            } else {
              _result.allergies = _cursor.getString(_cursorIndexOfAllergies);
            }
            if (_cursor.isNull(_cursorIndexOfEmergencyContactName)) {
              _result.emergencyContactName = null;
            } else {
              _result.emergencyContactName = _cursor.getString(_cursorIndexOfEmergencyContactName);
            }
            if (_cursor.isNull(_cursorIndexOfEmergencyContactPhone)) {
              _result.emergencyContactPhone = null;
            } else {
              _result.emergencyContactPhone = _cursor.getString(_cursorIndexOfEmergencyContactPhone);
            }
            if (_cursor.isNull(_cursorIndexOfAshaWorkerName)) {
              _result.ashaWorkerName = null;
            } else {
              _result.ashaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            }
            if (_cursor.isNull(_cursorIndexOfAshaWorkerPhone)) {
              _result.ashaWorkerPhone = null;
            } else {
              _result.ashaWorkerPhone = _cursor.getString(_cursorIndexOfAshaWorkerPhone);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public UserProfile getProfileSync() {
    final String _sql = "SELECT * FROM user_profile WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
      final int _cursorIndexOfBloodGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_group");
      final int _cursorIndexOfChronicConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "chronic_conditions");
      final int _cursorIndexOfAllergies = CursorUtil.getColumnIndexOrThrow(_cursor, "allergies");
      final int _cursorIndexOfEmergencyContactName = CursorUtil.getColumnIndexOrThrow(_cursor, "emergency_contact_name");
      final int _cursorIndexOfEmergencyContactPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "emergency_contact_phone");
      final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
      final int _cursorIndexOfAshaWorkerPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_phone");
      final UserProfile _result;
      if (_cursor.moveToFirst()) {
        _result = new UserProfile();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _result.name = null;
        } else {
          _result.name = _cursor.getString(_cursorIndexOfName);
        }
        _result.age = _cursor.getInt(_cursorIndexOfAge);
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _result.gender = null;
        } else {
          _result.gender = _cursor.getString(_cursorIndexOfGender);
        }
        if (_cursor.isNull(_cursorIndexOfBloodGroup)) {
          _result.bloodGroup = null;
        } else {
          _result.bloodGroup = _cursor.getString(_cursorIndexOfBloodGroup);
        }
        if (_cursor.isNull(_cursorIndexOfChronicConditions)) {
          _result.chronicConditions = null;
        } else {
          _result.chronicConditions = _cursor.getString(_cursorIndexOfChronicConditions);
        }
        if (_cursor.isNull(_cursorIndexOfAllergies)) {
          _result.allergies = null;
        } else {
          _result.allergies = _cursor.getString(_cursorIndexOfAllergies);
        }
        if (_cursor.isNull(_cursorIndexOfEmergencyContactName)) {
          _result.emergencyContactName = null;
        } else {
          _result.emergencyContactName = _cursor.getString(_cursorIndexOfEmergencyContactName);
        }
        if (_cursor.isNull(_cursorIndexOfEmergencyContactPhone)) {
          _result.emergencyContactPhone = null;
        } else {
          _result.emergencyContactPhone = _cursor.getString(_cursorIndexOfEmergencyContactPhone);
        }
        if (_cursor.isNull(_cursorIndexOfAshaWorkerName)) {
          _result.ashaWorkerName = null;
        } else {
          _result.ashaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
        }
        if (_cursor.isNull(_cursorIndexOfAshaWorkerPhone)) {
          _result.ashaWorkerPhone = null;
        } else {
          _result.ashaWorkerPhone = _cursor.getString(_cursorIndexOfAshaWorkerPhone);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getProfileCount() {
    final String _sql = "SELECT COUNT(*) FROM user_profile";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

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
import com.arogyasahaya.app.data.entity.VitalLog;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VitalLogDao_Impl implements VitalLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VitalLog> __insertionAdapterOfVitalLog;

  private final EntityDeletionOrUpdateAdapter<VitalLog> __deletionAdapterOfVitalLog;

  private final EntityDeletionOrUpdateAdapter<VitalLog> __updateAdapterOfVitalLog;

  public VitalLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVitalLog = new EntityInsertionAdapter<VitalLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `vital_logs` (`id`,`systolic`,`diastolic`,`heart_rate`,`glucose_level`,`weight`,`notes`,`recorded_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final VitalLog entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.systolic);
        statement.bindLong(3, entity.diastolic);
        statement.bindLong(4, entity.heartRate);
        statement.bindLong(5, entity.glucoseLevel);
        statement.bindDouble(6, entity.weight);
        if (entity.notes == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.notes);
        }
        statement.bindLong(8, entity.recordedAt);
      }
    };
    this.__deletionAdapterOfVitalLog = new EntityDeletionOrUpdateAdapter<VitalLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `vital_logs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final VitalLog entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfVitalLog = new EntityDeletionOrUpdateAdapter<VitalLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `vital_logs` SET `id` = ?,`systolic` = ?,`diastolic` = ?,`heart_rate` = ?,`glucose_level` = ?,`weight` = ?,`notes` = ?,`recorded_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final VitalLog entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.systolic);
        statement.bindLong(3, entity.diastolic);
        statement.bindLong(4, entity.heartRate);
        statement.bindLong(5, entity.glucoseLevel);
        statement.bindDouble(6, entity.weight);
        if (entity.notes == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.notes);
        }
        statement.bindLong(8, entity.recordedAt);
        statement.bindLong(9, entity.id);
      }
    };
  }

  @Override
  public long insert(final VitalLog vitalLog) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfVitalLog.insertAndReturnId(vitalLog);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final VitalLog vitalLog) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfVitalLog.handle(vitalLog);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final VitalLog vitalLog) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfVitalLog.handle(vitalLog);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<VitalLog>> getAllVitalLogs() {
    final String _sql = "SELECT * FROM vital_logs ORDER BY recorded_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"vital_logs"}, false, new Callable<List<VitalLog>>() {
      @Override
      @Nullable
      public List<VitalLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
          final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
          final List<VitalLog> _result = new ArrayList<VitalLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VitalLog _item;
            _item = new VitalLog();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _item.systolic = _cursor.getInt(_cursorIndexOfSystolic);
            _item.diastolic = _cursor.getInt(_cursorIndexOfDiastolic);
            _item.heartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            _item.glucoseLevel = _cursor.getInt(_cursorIndexOfGlucoseLevel);
            _item.weight = _cursor.getFloat(_cursorIndexOfWeight);
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _item.notes = null;
            } else {
              _item.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.recordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            _result.add(_item);
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
  public LiveData<List<VitalLog>> getLast7DaysVitals() {
    final String _sql = "SELECT * FROM vital_logs ORDER BY recorded_at DESC LIMIT 7";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"vital_logs"}, false, new Callable<List<VitalLog>>() {
      @Override
      @Nullable
      public List<VitalLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
          final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
          final List<VitalLog> _result = new ArrayList<VitalLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VitalLog _item;
            _item = new VitalLog();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _item.systolic = _cursor.getInt(_cursorIndexOfSystolic);
            _item.diastolic = _cursor.getInt(_cursorIndexOfDiastolic);
            _item.heartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            _item.glucoseLevel = _cursor.getInt(_cursorIndexOfGlucoseLevel);
            _item.weight = _cursor.getFloat(_cursorIndexOfWeight);
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _item.notes = null;
            } else {
              _item.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.recordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            _result.add(_item);
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
  public List<VitalLog> getLast7DaysVitalsSync() {
    final String _sql = "SELECT * FROM vital_logs ORDER BY recorded_at DESC LIMIT 7";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
      final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
      final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
      final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
      final List<VitalLog> _result = new ArrayList<VitalLog>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final VitalLog _item;
        _item = new VitalLog();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _item.systolic = _cursor.getInt(_cursorIndexOfSystolic);
        _item.diastolic = _cursor.getInt(_cursorIndexOfDiastolic);
        _item.heartRate = _cursor.getInt(_cursorIndexOfHeartRate);
        _item.glucoseLevel = _cursor.getInt(_cursorIndexOfGlucoseLevel);
        _item.weight = _cursor.getFloat(_cursorIndexOfWeight);
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _item.notes = null;
        } else {
          _item.notes = _cursor.getString(_cursorIndexOfNotes);
        }
        _item.recordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<VitalLog> getLatestVital() {
    final String _sql = "SELECT * FROM vital_logs ORDER BY recorded_at DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"vital_logs"}, false, new Callable<VitalLog>() {
      @Override
      @Nullable
      public VitalLog call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
          final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
          final VitalLog _result;
          if (_cursor.moveToFirst()) {
            _result = new VitalLog();
            _result.id = _cursor.getInt(_cursorIndexOfId);
            _result.systolic = _cursor.getInt(_cursorIndexOfSystolic);
            _result.diastolic = _cursor.getInt(_cursorIndexOfDiastolic);
            _result.heartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            _result.glucoseLevel = _cursor.getInt(_cursorIndexOfGlucoseLevel);
            _result.weight = _cursor.getFloat(_cursorIndexOfWeight);
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _result.notes = null;
            } else {
              _result.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result.recordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
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
  public LiveData<List<VitalLog>> getVitalsSince(final long fromTime) {
    final String _sql = "SELECT * FROM vital_logs WHERE recorded_at >= ? ORDER BY recorded_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromTime);
    return __db.getInvalidationTracker().createLiveData(new String[] {"vital_logs"}, false, new Callable<List<VitalLog>>() {
      @Override
      @Nullable
      public List<VitalLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
          final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
          final List<VitalLog> _result = new ArrayList<VitalLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VitalLog _item;
            _item = new VitalLog();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _item.systolic = _cursor.getInt(_cursorIndexOfSystolic);
            _item.diastolic = _cursor.getInt(_cursorIndexOfDiastolic);
            _item.heartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            _item.glucoseLevel = _cursor.getInt(_cursorIndexOfGlucoseLevel);
            _item.weight = _cursor.getFloat(_cursorIndexOfWeight);
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _item.notes = null;
            } else {
              _item.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.recordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            _result.add(_item);
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
  public LiveData<List<VitalLog>> getLast30DaysVitals() {
    final String _sql = "SELECT * FROM vital_logs ORDER BY recorded_at DESC LIMIT 30";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"vital_logs"}, false, new Callable<List<VitalLog>>() {
      @Override
      @Nullable
      public List<VitalLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
          final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
          final List<VitalLog> _result = new ArrayList<VitalLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VitalLog _item;
            _item = new VitalLog();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _item.systolic = _cursor.getInt(_cursorIndexOfSystolic);
            _item.diastolic = _cursor.getInt(_cursorIndexOfDiastolic);
            _item.heartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            _item.glucoseLevel = _cursor.getInt(_cursorIndexOfGlucoseLevel);
            _item.weight = _cursor.getFloat(_cursorIndexOfWeight);
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _item.notes = null;
            } else {
              _item.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.recordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            _result.add(_item);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

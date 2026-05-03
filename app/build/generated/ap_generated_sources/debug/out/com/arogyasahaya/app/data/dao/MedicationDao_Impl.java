package com.arogyasahaya.app.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.arogyasahaya.app.data.entity.Medication;
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
public final class MedicationDao_Impl implements MedicationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Medication> __insertionAdapterOfMedication;

  private final EntityDeletionOrUpdateAdapter<Medication> __deletionAdapterOfMedication;

  private final EntityDeletionOrUpdateAdapter<Medication> __updateAdapterOfMedication;

  private final SharedSQLiteStatement __preparedStmtOfSoftDelete;

  public MedicationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMedication = new EntityInsertionAdapter<Medication>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `medications` (`id`,`name`,`dosage`,`morning`,`afternoon`,`night`,`notes`,`is_active`,`created_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Medication entity) {
        statement.bindLong(1, entity.id);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        if (entity.dosage == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.dosage);
        }
        final int _tmp = entity.morning ? 1 : 0;
        statement.bindLong(4, _tmp);
        final int _tmp_1 = entity.afternoon ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        final int _tmp_2 = entity.night ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        if (entity.notes == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.notes);
        }
        final int _tmp_3 = entity.isActive ? 1 : 0;
        statement.bindLong(8, _tmp_3);
        statement.bindLong(9, entity.createdAt);
      }
    };
    this.__deletionAdapterOfMedication = new EntityDeletionOrUpdateAdapter<Medication>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `medications` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Medication entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfMedication = new EntityDeletionOrUpdateAdapter<Medication>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `medications` SET `id` = ?,`name` = ?,`dosage` = ?,`morning` = ?,`afternoon` = ?,`night` = ?,`notes` = ?,`is_active` = ?,`created_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Medication entity) {
        statement.bindLong(1, entity.id);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        if (entity.dosage == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.dosage);
        }
        final int _tmp = entity.morning ? 1 : 0;
        statement.bindLong(4, _tmp);
        final int _tmp_1 = entity.afternoon ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        final int _tmp_2 = entity.night ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        if (entity.notes == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.notes);
        }
        final int _tmp_3 = entity.isActive ? 1 : 0;
        statement.bindLong(8, _tmp_3);
        statement.bindLong(9, entity.createdAt);
        statement.bindLong(10, entity.id);
      }
    };
    this.__preparedStmtOfSoftDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE medications SET is_active = 0 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final Medication medication) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfMedication.insertAndReturnId(medication);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Medication medication) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMedication.handle(medication);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Medication medication) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMedication.handle(medication);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void softDelete(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSoftDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfSoftDelete.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Medication>> getAllActiveMedications() {
    final String _sql = "SELECT * FROM medications WHERE is_active = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"medications"}, false, new Callable<List<Medication>>() {
      @Override
      @Nullable
      public List<Medication> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfMorning = CursorUtil.getColumnIndexOrThrow(_cursor, "morning");
          final int _cursorIndexOfAfternoon = CursorUtil.getColumnIndexOrThrow(_cursor, "afternoon");
          final int _cursorIndexOfNight = CursorUtil.getColumnIndexOrThrow(_cursor, "night");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final List<Medication> _result = new ArrayList<Medication>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Medication _item;
            _item = new Medication();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfName)) {
              _item.name = null;
            } else {
              _item.name = _cursor.getString(_cursorIndexOfName);
            }
            if (_cursor.isNull(_cursorIndexOfDosage)) {
              _item.dosage = null;
            } else {
              _item.dosage = _cursor.getString(_cursorIndexOfDosage);
            }
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMorning);
            _item.morning = _tmp != 0;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfAfternoon);
            _item.afternoon = _tmp_1 != 0;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfNight);
            _item.night = _tmp_2 != 0;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _item.notes = null;
            } else {
              _item.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _item.isActive = _tmp_3 != 0;
            _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
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
  public List<Medication> getAllActiveMedicationsSync() {
    final String _sql = "SELECT * FROM medications WHERE is_active = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
      final int _cursorIndexOfMorning = CursorUtil.getColumnIndexOrThrow(_cursor, "morning");
      final int _cursorIndexOfAfternoon = CursorUtil.getColumnIndexOrThrow(_cursor, "afternoon");
      final int _cursorIndexOfNight = CursorUtil.getColumnIndexOrThrow(_cursor, "night");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final List<Medication> _result = new ArrayList<Medication>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Medication _item;
        _item = new Medication();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _item.name = null;
        } else {
          _item.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfDosage)) {
          _item.dosage = null;
        } else {
          _item.dosage = _cursor.getString(_cursorIndexOfDosage);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfMorning);
        _item.morning = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAfternoon);
        _item.afternoon = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfNight);
        _item.night = _tmp_2 != 0;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _item.notes = null;
        } else {
          _item.notes = _cursor.getString(_cursorIndexOfNotes);
        }
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
        _item.isActive = _tmp_3 != 0;
        _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<Medication> getMedicationById(final int id) {
    final String _sql = "SELECT * FROM medications WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[] {"medications"}, false, new Callable<Medication>() {
      @Override
      @Nullable
      public Medication call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfMorning = CursorUtil.getColumnIndexOrThrow(_cursor, "morning");
          final int _cursorIndexOfAfternoon = CursorUtil.getColumnIndexOrThrow(_cursor, "afternoon");
          final int _cursorIndexOfNight = CursorUtil.getColumnIndexOrThrow(_cursor, "night");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final Medication _result;
          if (_cursor.moveToFirst()) {
            _result = new Medication();
            _result.id = _cursor.getInt(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfName)) {
              _result.name = null;
            } else {
              _result.name = _cursor.getString(_cursorIndexOfName);
            }
            if (_cursor.isNull(_cursorIndexOfDosage)) {
              _result.dosage = null;
            } else {
              _result.dosage = _cursor.getString(_cursorIndexOfDosage);
            }
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMorning);
            _result.morning = _tmp != 0;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfAfternoon);
            _result.afternoon = _tmp_1 != 0;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfNight);
            _result.night = _tmp_2 != 0;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _result.notes = null;
            } else {
              _result.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _result.isActive = _tmp_3 != 0;
            _result.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
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
  public List<Medication> getMorningMedications() {
    final String _sql = "SELECT * FROM medications WHERE morning = 1 AND is_active = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
      final int _cursorIndexOfMorning = CursorUtil.getColumnIndexOrThrow(_cursor, "morning");
      final int _cursorIndexOfAfternoon = CursorUtil.getColumnIndexOrThrow(_cursor, "afternoon");
      final int _cursorIndexOfNight = CursorUtil.getColumnIndexOrThrow(_cursor, "night");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final List<Medication> _result = new ArrayList<Medication>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Medication _item;
        _item = new Medication();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _item.name = null;
        } else {
          _item.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfDosage)) {
          _item.dosage = null;
        } else {
          _item.dosage = _cursor.getString(_cursorIndexOfDosage);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfMorning);
        _item.morning = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAfternoon);
        _item.afternoon = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfNight);
        _item.night = _tmp_2 != 0;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _item.notes = null;
        } else {
          _item.notes = _cursor.getString(_cursorIndexOfNotes);
        }
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
        _item.isActive = _tmp_3 != 0;
        _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Medication> getAfternoonMedications() {
    final String _sql = "SELECT * FROM medications WHERE afternoon = 1 AND is_active = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
      final int _cursorIndexOfMorning = CursorUtil.getColumnIndexOrThrow(_cursor, "morning");
      final int _cursorIndexOfAfternoon = CursorUtil.getColumnIndexOrThrow(_cursor, "afternoon");
      final int _cursorIndexOfNight = CursorUtil.getColumnIndexOrThrow(_cursor, "night");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final List<Medication> _result = new ArrayList<Medication>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Medication _item;
        _item = new Medication();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _item.name = null;
        } else {
          _item.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfDosage)) {
          _item.dosage = null;
        } else {
          _item.dosage = _cursor.getString(_cursorIndexOfDosage);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfMorning);
        _item.morning = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAfternoon);
        _item.afternoon = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfNight);
        _item.night = _tmp_2 != 0;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _item.notes = null;
        } else {
          _item.notes = _cursor.getString(_cursorIndexOfNotes);
        }
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
        _item.isActive = _tmp_3 != 0;
        _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Medication> getNightMedications() {
    final String _sql = "SELECT * FROM medications WHERE night = 1 AND is_active = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
      final int _cursorIndexOfMorning = CursorUtil.getColumnIndexOrThrow(_cursor, "morning");
      final int _cursorIndexOfAfternoon = CursorUtil.getColumnIndexOrThrow(_cursor, "afternoon");
      final int _cursorIndexOfNight = CursorUtil.getColumnIndexOrThrow(_cursor, "night");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final List<Medication> _result = new ArrayList<Medication>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Medication _item;
        _item = new Medication();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _item.name = null;
        } else {
          _item.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfDosage)) {
          _item.dosage = null;
        } else {
          _item.dosage = _cursor.getString(_cursorIndexOfDosage);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfMorning);
        _item.morning = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAfternoon);
        _item.afternoon = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfNight);
        _item.night = _tmp_2 != 0;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _item.notes = null;
        } else {
          _item.notes = _cursor.getString(_cursorIndexOfNotes);
        }
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
        _item.isActive = _tmp_3 != 0;
        _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getActiveMedicationCount() {
    final String _sql = "SELECT COUNT(*) FROM medications WHERE is_active = 1";
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

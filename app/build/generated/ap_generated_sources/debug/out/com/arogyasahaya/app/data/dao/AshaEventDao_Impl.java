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
import com.arogyasahaya.app.data.entity.AshaEvent;
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
public final class AshaEventDao_Impl implements AshaEventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AshaEvent> __insertionAdapterOfAshaEvent;

  private final EntityDeletionOrUpdateAdapter<AshaEvent> __deletionAdapterOfAshaEvent;

  private final EntityDeletionOrUpdateAdapter<AshaEvent> __updateAdapterOfAshaEvent;

  private final SharedSQLiteStatement __preparedStmtOfRegisterForEvent;

  public AshaEventDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAshaEvent = new EntityInsertionAdapter<AshaEvent>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `asha_events` (`id`,`title`,`description`,`location`,`event_date`,`event_type`,`is_registered`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final AshaEvent entity) {
        statement.bindLong(1, entity.id);
        if (entity.title == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.title);
        }
        if (entity.description == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.description);
        }
        if (entity.location == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.location);
        }
        statement.bindLong(5, entity.eventDate);
        if (entity.eventType == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.eventType);
        }
        final int _tmp = entity.isRegistered ? 1 : 0;
        statement.bindLong(7, _tmp);
      }
    };
    this.__deletionAdapterOfAshaEvent = new EntityDeletionOrUpdateAdapter<AshaEvent>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `asha_events` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final AshaEvent entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfAshaEvent = new EntityDeletionOrUpdateAdapter<AshaEvent>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `asha_events` SET `id` = ?,`title` = ?,`description` = ?,`location` = ?,`event_date` = ?,`event_type` = ?,`is_registered` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final AshaEvent entity) {
        statement.bindLong(1, entity.id);
        if (entity.title == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.title);
        }
        if (entity.description == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.description);
        }
        if (entity.location == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.location);
        }
        statement.bindLong(5, entity.eventDate);
        if (entity.eventType == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.eventType);
        }
        final int _tmp = entity.isRegistered ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.id);
      }
    };
    this.__preparedStmtOfRegisterForEvent = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE asha_events SET is_registered = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final AshaEvent event) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfAshaEvent.insertAndReturnId(event);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final AshaEvent event) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAshaEvent.handle(event);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final AshaEvent event) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAshaEvent.handle(event);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void registerForEvent(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRegisterForEvent.acquire();
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
      __preparedStmtOfRegisterForEvent.release(_stmt);
    }
  }

  @Override
  public LiveData<List<AshaEvent>> getAllEvents() {
    final String _sql = "SELECT * FROM asha_events ORDER BY event_date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"asha_events"}, false, new Callable<List<AshaEvent>>() {
      @Override
      @Nullable
      public List<AshaEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfEventDate = CursorUtil.getColumnIndexOrThrow(_cursor, "event_date");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfIsRegistered = CursorUtil.getColumnIndexOrThrow(_cursor, "is_registered");
          final List<AshaEvent> _result = new ArrayList<AshaEvent>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AshaEvent _item;
            _item = new AshaEvent();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _item.title = null;
            } else {
              _item.title = _cursor.getString(_cursorIndexOfTitle);
            }
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _item.description = null;
            } else {
              _item.description = _cursor.getString(_cursorIndexOfDescription);
            }
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _item.location = null;
            } else {
              _item.location = _cursor.getString(_cursorIndexOfLocation);
            }
            _item.eventDate = _cursor.getLong(_cursorIndexOfEventDate);
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _item.eventType = null;
            } else {
              _item.eventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRegistered);
            _item.isRegistered = _tmp != 0;
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
  public LiveData<List<AshaEvent>> getUpcomingEvents(final long now) {
    final String _sql = "SELECT * FROM asha_events WHERE event_date >= ? ORDER BY event_date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, now);
    return __db.getInvalidationTracker().createLiveData(new String[] {"asha_events"}, false, new Callable<List<AshaEvent>>() {
      @Override
      @Nullable
      public List<AshaEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfEventDate = CursorUtil.getColumnIndexOrThrow(_cursor, "event_date");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfIsRegistered = CursorUtil.getColumnIndexOrThrow(_cursor, "is_registered");
          final List<AshaEvent> _result = new ArrayList<AshaEvent>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AshaEvent _item;
            _item = new AshaEvent();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _item.title = null;
            } else {
              _item.title = _cursor.getString(_cursorIndexOfTitle);
            }
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _item.description = null;
            } else {
              _item.description = _cursor.getString(_cursorIndexOfDescription);
            }
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _item.location = null;
            } else {
              _item.location = _cursor.getString(_cursorIndexOfLocation);
            }
            _item.eventDate = _cursor.getLong(_cursorIndexOfEventDate);
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _item.eventType = null;
            } else {
              _item.eventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRegistered);
            _item.isRegistered = _tmp != 0;
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
  public LiveData<AshaEvent> getNextEvent(final long now) {
    final String _sql = "SELECT * FROM asha_events WHERE event_date >= ? ORDER BY event_date ASC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, now);
    return __db.getInvalidationTracker().createLiveData(new String[] {"asha_events"}, false, new Callable<AshaEvent>() {
      @Override
      @Nullable
      public AshaEvent call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfEventDate = CursorUtil.getColumnIndexOrThrow(_cursor, "event_date");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfIsRegistered = CursorUtil.getColumnIndexOrThrow(_cursor, "is_registered");
          final AshaEvent _result;
          if (_cursor.moveToFirst()) {
            _result = new AshaEvent();
            _result.id = _cursor.getInt(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _result.title = null;
            } else {
              _result.title = _cursor.getString(_cursorIndexOfTitle);
            }
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _result.description = null;
            } else {
              _result.description = _cursor.getString(_cursorIndexOfDescription);
            }
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _result.location = null;
            } else {
              _result.location = _cursor.getString(_cursorIndexOfLocation);
            }
            _result.eventDate = _cursor.getLong(_cursorIndexOfEventDate);
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _result.eventType = null;
            } else {
              _result.eventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRegistered);
            _result.isRegistered = _tmp != 0;
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
  public int getEventCount() {
    final String _sql = "SELECT COUNT(*) FROM asha_events";
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

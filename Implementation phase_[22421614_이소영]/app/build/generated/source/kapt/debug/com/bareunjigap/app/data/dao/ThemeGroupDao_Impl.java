package com.bareunjigap.app.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bareunjigap.app.data.entity.ThemeGroup;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ThemeGroupDao_Impl implements ThemeGroupDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ThemeGroup> __insertionAdapterOfThemeGroup;

  private final EntityDeletionOrUpdateAdapter<ThemeGroup> __deletionAdapterOfThemeGroup;

  private final EntityDeletionOrUpdateAdapter<ThemeGroup> __updateAdapterOfThemeGroup;

  public ThemeGroupDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfThemeGroup = new EntityInsertionAdapter<ThemeGroup>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `theme_groups` (`themeId`,`userId`,`name`,`targetBudget`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ThemeGroup entity) {
        statement.bindLong(1, entity.getThemeId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        statement.bindLong(4, entity.getTargetBudget());
        statement.bindLong(5, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfThemeGroup = new EntityDeletionOrUpdateAdapter<ThemeGroup>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `theme_groups` WHERE `themeId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ThemeGroup entity) {
        statement.bindLong(1, entity.getThemeId());
      }
    };
    this.__updateAdapterOfThemeGroup = new EntityDeletionOrUpdateAdapter<ThemeGroup>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `theme_groups` SET `themeId` = ?,`userId` = ?,`name` = ?,`targetBudget` = ?,`createdAt` = ? WHERE `themeId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ThemeGroup entity) {
        statement.bindLong(1, entity.getThemeId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        statement.bindLong(4, entity.getTargetBudget());
        statement.bindLong(5, entity.getCreatedAt());
        statement.bindLong(6, entity.getThemeId());
      }
    };
  }

  @Override
  public Object insert(final ThemeGroup theme, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfThemeGroup.insertAndReturnId(theme);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final ThemeGroup theme, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfThemeGroup.handle(theme);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ThemeGroup theme, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfThemeGroup.handle(theme);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<ThemeGroup>> getAllByUser(final int userId) {
    final String _sql = "SELECT * FROM theme_groups WHERE userId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"theme_groups"}, false, new Callable<List<ThemeGroup>>() {
      @Override
      @Nullable
      public List<ThemeGroup> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTargetBudget = CursorUtil.getColumnIndexOrThrow(_cursor, "targetBudget");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ThemeGroup> _result = new ArrayList<ThemeGroup>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ThemeGroup _item;
            final int _tmpThemeId;
            _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpTargetBudget;
            _tmpTargetBudget = _cursor.getInt(_cursorIndexOfTargetBudget);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ThemeGroup(_tmpThemeId,_tmpUserId,_tmpName,_tmpTargetBudget,_tmpCreatedAt);
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
  public Object findById(final int id, final Continuation<? super ThemeGroup> $completion) {
    final String _sql = "SELECT * FROM theme_groups WHERE themeId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ThemeGroup>() {
      @Override
      @Nullable
      public ThemeGroup call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTargetBudget = CursorUtil.getColumnIndexOrThrow(_cursor, "targetBudget");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ThemeGroup _result;
          if (_cursor.moveToFirst()) {
            final int _tmpThemeId;
            _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpTargetBudget;
            _tmpTargetBudget = _cursor.getInt(_cursorIndexOfTargetBudget);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ThemeGroup(_tmpThemeId,_tmpUserId,_tmpName,_tmpTargetBudget,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

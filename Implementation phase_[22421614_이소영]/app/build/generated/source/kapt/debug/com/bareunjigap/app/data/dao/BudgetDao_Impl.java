package com.bareunjigap.app.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bareunjigap.app.data.entity.Budget;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BudgetDao_Impl implements BudgetDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Budget> __insertionAdapterOfBudget;

  private final EntityDeletionOrUpdateAdapter<Budget> __updateAdapterOfBudget;

  public BudgetDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBudget = new EntityInsertionAdapter<Budget>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `budgets` (`budgetId`,`userId`,`monthlyIncome`,`fixedExpense`,`warningThreshold`,`yearMonth`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Budget entity) {
        statement.bindLong(1, entity.getBudgetId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getMonthlyIncome());
        statement.bindLong(4, entity.getFixedExpense());
        statement.bindDouble(5, entity.getWarningThreshold());
        if (entity.getYearMonth() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getYearMonth());
        }
      }
    };
    this.__updateAdapterOfBudget = new EntityDeletionOrUpdateAdapter<Budget>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `budgets` SET `budgetId` = ?,`userId` = ?,`monthlyIncome` = ?,`fixedExpense` = ?,`warningThreshold` = ?,`yearMonth` = ? WHERE `budgetId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Budget entity) {
        statement.bindLong(1, entity.getBudgetId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getMonthlyIncome());
        statement.bindLong(4, entity.getFixedExpense());
        statement.bindDouble(5, entity.getWarningThreshold());
        if (entity.getYearMonth() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getYearMonth());
        }
        statement.bindLong(7, entity.getBudgetId());
      }
    };
  }

  @Override
  public Object insert(final Budget budget, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfBudget.insertAndReturnId(budget);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Budget budget, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfBudget.handle(budget);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getByMonth(final int userId, final String yearMonth,
      final Continuation<? super Budget> $completion) {
    final String _sql = "SELECT * FROM budgets WHERE userId = ? AND yearMonth = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Budget>() {
      @Override
      @Nullable
      public Budget call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBudgetId = CursorUtil.getColumnIndexOrThrow(_cursor, "budgetId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyIncome");
          final int _cursorIndexOfFixedExpense = CursorUtil.getColumnIndexOrThrow(_cursor, "fixedExpense");
          final int _cursorIndexOfWarningThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "warningThreshold");
          final int _cursorIndexOfYearMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "yearMonth");
          final Budget _result;
          if (_cursor.moveToFirst()) {
            final int _tmpBudgetId;
            _tmpBudgetId = _cursor.getInt(_cursorIndexOfBudgetId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpMonthlyIncome;
            _tmpMonthlyIncome = _cursor.getInt(_cursorIndexOfMonthlyIncome);
            final int _tmpFixedExpense;
            _tmpFixedExpense = _cursor.getInt(_cursorIndexOfFixedExpense);
            final float _tmpWarningThreshold;
            _tmpWarningThreshold = _cursor.getFloat(_cursorIndexOfWarningThreshold);
            final String _tmpYearMonth;
            if (_cursor.isNull(_cursorIndexOfYearMonth)) {
              _tmpYearMonth = null;
            } else {
              _tmpYearMonth = _cursor.getString(_cursorIndexOfYearMonth);
            }
            _result = new Budget(_tmpBudgetId,_tmpUserId,_tmpMonthlyIncome,_tmpFixedExpense,_tmpWarningThreshold,_tmpYearMonth);
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

  @Override
  public Object getLatest(final int userId, final Continuation<? super Budget> $completion) {
    final String _sql = "SELECT * FROM budgets WHERE userId = ? ORDER BY yearMonth DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Budget>() {
      @Override
      @Nullable
      public Budget call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBudgetId = CursorUtil.getColumnIndexOrThrow(_cursor, "budgetId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyIncome");
          final int _cursorIndexOfFixedExpense = CursorUtil.getColumnIndexOrThrow(_cursor, "fixedExpense");
          final int _cursorIndexOfWarningThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "warningThreshold");
          final int _cursorIndexOfYearMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "yearMonth");
          final Budget _result;
          if (_cursor.moveToFirst()) {
            final int _tmpBudgetId;
            _tmpBudgetId = _cursor.getInt(_cursorIndexOfBudgetId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpMonthlyIncome;
            _tmpMonthlyIncome = _cursor.getInt(_cursorIndexOfMonthlyIncome);
            final int _tmpFixedExpense;
            _tmpFixedExpense = _cursor.getInt(_cursorIndexOfFixedExpense);
            final float _tmpWarningThreshold;
            _tmpWarningThreshold = _cursor.getFloat(_cursorIndexOfWarningThreshold);
            final String _tmpYearMonth;
            if (_cursor.isNull(_cursorIndexOfYearMonth)) {
              _tmpYearMonth = null;
            } else {
              _tmpYearMonth = _cursor.getString(_cursorIndexOfYearMonth);
            }
            _result = new Budget(_tmpBudgetId,_tmpUserId,_tmpMonthlyIncome,_tmpFixedExpense,_tmpWarningThreshold,_tmpYearMonth);
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

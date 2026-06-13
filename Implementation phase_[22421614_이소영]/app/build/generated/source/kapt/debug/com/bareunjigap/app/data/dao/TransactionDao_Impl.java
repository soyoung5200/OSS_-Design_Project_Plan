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
import com.bareunjigap.app.data.entity.Transaction;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Transaction> __deletionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Transaction> __updateAdapterOfTransaction;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`txId`,`userId`,`amount`,`date`,`merchant`,`categoryId`,`themeId`,`memo`,`isEdited`,`isDutchPay`,`originalAmount`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getTxId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getAmount());
        statement.bindLong(4, entity.getDate());
        if (entity.getMerchant() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMerchant());
        }
        statement.bindLong(6, entity.getCategoryId());
        if (entity.getThemeId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getThemeId());
        }
        if (entity.getMemo() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getMemo());
        }
        final int _tmp = entity.isEdited() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.isDutchPay() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        if (entity.getOriginalAmount() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getOriginalAmount());
        }
      }
    };
    this.__deletionAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transactions` WHERE `txId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getTxId());
      }
    };
    this.__updateAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `txId` = ?,`userId` = ?,`amount` = ?,`date` = ?,`merchant` = ?,`categoryId` = ?,`themeId` = ?,`memo` = ?,`isEdited` = ?,`isDutchPay` = ?,`originalAmount` = ? WHERE `txId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getTxId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getAmount());
        statement.bindLong(4, entity.getDate());
        if (entity.getMerchant() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMerchant());
        }
        statement.bindLong(6, entity.getCategoryId());
        if (entity.getThemeId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getThemeId());
        }
        if (entity.getMemo() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getMemo());
        }
        final int _tmp = entity.isEdited() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.isDutchPay() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        if (entity.getOriginalAmount() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getOriginalAmount());
        }
        statement.bindLong(12, entity.getTxId());
      }
    };
  }

  @Override
  public Object insert(final Transaction transaction,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTransaction.insertAndReturnId(transaction);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Transaction>> getAllByUser(final int userId) {
    final String _sql = "SELECT * FROM transactions WHERE userId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTxId = CursorUtil.getColumnIndexOrThrow(_cursor, "txId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfIsEdited = CursorUtil.getColumnIndexOrThrow(_cursor, "isEdited");
          final int _cursorIndexOfIsDutchPay = CursorUtil.getColumnIndexOrThrow(_cursor, "isDutchPay");
          final int _cursorIndexOfOriginalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAmount");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpTxId;
            _tmpTxId = _cursor.getInt(_cursorIndexOfTxId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final Integer _tmpThemeId;
            if (_cursor.isNull(_cursorIndexOfThemeId)) {
              _tmpThemeId = null;
            } else {
              _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final boolean _tmpIsEdited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEdited);
            _tmpIsEdited = _tmp != 0;
            final boolean _tmpIsDutchPay;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDutchPay);
            _tmpIsDutchPay = _tmp_1 != 0;
            final Integer _tmpOriginalAmount;
            if (_cursor.isNull(_cursorIndexOfOriginalAmount)) {
              _tmpOriginalAmount = null;
            } else {
              _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
            }
            _item = new Transaction(_tmpTxId,_tmpUserId,_tmpAmount,_tmpDate,_tmpMerchant,_tmpCategoryId,_tmpThemeId,_tmpMemo,_tmpIsEdited,_tmpIsDutchPay,_tmpOriginalAmount);
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
  public Object getAllByUserSync(final int userId,
      final Continuation<? super List<Transaction>> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE userId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTxId = CursorUtil.getColumnIndexOrThrow(_cursor, "txId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfIsEdited = CursorUtil.getColumnIndexOrThrow(_cursor, "isEdited");
          final int _cursorIndexOfIsDutchPay = CursorUtil.getColumnIndexOrThrow(_cursor, "isDutchPay");
          final int _cursorIndexOfOriginalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAmount");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpTxId;
            _tmpTxId = _cursor.getInt(_cursorIndexOfTxId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final Integer _tmpThemeId;
            if (_cursor.isNull(_cursorIndexOfThemeId)) {
              _tmpThemeId = null;
            } else {
              _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final boolean _tmpIsEdited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEdited);
            _tmpIsEdited = _tmp != 0;
            final boolean _tmpIsDutchPay;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDutchPay);
            _tmpIsDutchPay = _tmp_1 != 0;
            final Integer _tmpOriginalAmount;
            if (_cursor.isNull(_cursorIndexOfOriginalAmount)) {
              _tmpOriginalAmount = null;
            } else {
              _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
            }
            _item = new Transaction(_tmpTxId,_tmpUserId,_tmpAmount,_tmpDate,_tmpMerchant,_tmpCategoryId,_tmpThemeId,_tmpMemo,_tmpIsEdited,_tmpIsDutchPay,_tmpOriginalAmount);
            _result.add(_item);
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
  public LiveData<List<Transaction>> getByMonth(final int userId, final long startMs,
      final long endMs) {
    final String _sql = "SELECT * FROM transactions WHERE userId = ? AND date >= ? AND date <= ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startMs);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endMs);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTxId = CursorUtil.getColumnIndexOrThrow(_cursor, "txId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfIsEdited = CursorUtil.getColumnIndexOrThrow(_cursor, "isEdited");
          final int _cursorIndexOfIsDutchPay = CursorUtil.getColumnIndexOrThrow(_cursor, "isDutchPay");
          final int _cursorIndexOfOriginalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAmount");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpTxId;
            _tmpTxId = _cursor.getInt(_cursorIndexOfTxId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final Integer _tmpThemeId;
            if (_cursor.isNull(_cursorIndexOfThemeId)) {
              _tmpThemeId = null;
            } else {
              _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final boolean _tmpIsEdited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEdited);
            _tmpIsEdited = _tmp != 0;
            final boolean _tmpIsDutchPay;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDutchPay);
            _tmpIsDutchPay = _tmp_1 != 0;
            final Integer _tmpOriginalAmount;
            if (_cursor.isNull(_cursorIndexOfOriginalAmount)) {
              _tmpOriginalAmount = null;
            } else {
              _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
            }
            _item = new Transaction(_tmpTxId,_tmpUserId,_tmpAmount,_tmpDate,_tmpMerchant,_tmpCategoryId,_tmpThemeId,_tmpMemo,_tmpIsEdited,_tmpIsDutchPay,_tmpOriginalAmount);
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
  public Object getByMonthSync(final int userId, final long startMs, final long endMs,
      final Continuation<? super List<Transaction>> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE userId = ? AND date >= ? AND date <= ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startMs);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endMs);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTxId = CursorUtil.getColumnIndexOrThrow(_cursor, "txId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfIsEdited = CursorUtil.getColumnIndexOrThrow(_cursor, "isEdited");
          final int _cursorIndexOfIsDutchPay = CursorUtil.getColumnIndexOrThrow(_cursor, "isDutchPay");
          final int _cursorIndexOfOriginalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAmount");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpTxId;
            _tmpTxId = _cursor.getInt(_cursorIndexOfTxId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final Integer _tmpThemeId;
            if (_cursor.isNull(_cursorIndexOfThemeId)) {
              _tmpThemeId = null;
            } else {
              _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final boolean _tmpIsEdited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEdited);
            _tmpIsEdited = _tmp != 0;
            final boolean _tmpIsDutchPay;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDutchPay);
            _tmpIsDutchPay = _tmp_1 != 0;
            final Integer _tmpOriginalAmount;
            if (_cursor.isNull(_cursorIndexOfOriginalAmount)) {
              _tmpOriginalAmount = null;
            } else {
              _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
            }
            _item = new Transaction(_tmpTxId,_tmpUserId,_tmpAmount,_tmpDate,_tmpMerchant,_tmpCategoryId,_tmpThemeId,_tmpMemo,_tmpIsEdited,_tmpIsDutchPay,_tmpOriginalAmount);
            _result.add(_item);
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
  public Object getTotalExpenseByMonth(final int userId, final long startMs, final long endMs,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT SUM(amount) FROM transactions WHERE userId = ? AND amount < 0 AND date >= ? AND date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startMs);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endMs);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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
  public LiveData<List<Transaction>> getByTheme(final int themeId) {
    final String _sql = "SELECT * FROM transactions WHERE themeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, themeId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTxId = CursorUtil.getColumnIndexOrThrow(_cursor, "txId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfIsEdited = CursorUtil.getColumnIndexOrThrow(_cursor, "isEdited");
          final int _cursorIndexOfIsDutchPay = CursorUtil.getColumnIndexOrThrow(_cursor, "isDutchPay");
          final int _cursorIndexOfOriginalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAmount");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpTxId;
            _tmpTxId = _cursor.getInt(_cursorIndexOfTxId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final Integer _tmpThemeId;
            if (_cursor.isNull(_cursorIndexOfThemeId)) {
              _tmpThemeId = null;
            } else {
              _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final boolean _tmpIsEdited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEdited);
            _tmpIsEdited = _tmp != 0;
            final boolean _tmpIsDutchPay;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDutchPay);
            _tmpIsDutchPay = _tmp_1 != 0;
            final Integer _tmpOriginalAmount;
            if (_cursor.isNull(_cursorIndexOfOriginalAmount)) {
              _tmpOriginalAmount = null;
            } else {
              _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
            }
            _item = new Transaction(_tmpTxId,_tmpUserId,_tmpAmount,_tmpDate,_tmpMerchant,_tmpCategoryId,_tmpThemeId,_tmpMemo,_tmpIsEdited,_tmpIsDutchPay,_tmpOriginalAmount);
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
  public Object getById(final int txId, final Continuation<? super Transaction> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE txId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, txId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Transaction>() {
      @Override
      @Nullable
      public Transaction call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTxId = CursorUtil.getColumnIndexOrThrow(_cursor, "txId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfIsEdited = CursorUtil.getColumnIndexOrThrow(_cursor, "isEdited");
          final int _cursorIndexOfIsDutchPay = CursorUtil.getColumnIndexOrThrow(_cursor, "isDutchPay");
          final int _cursorIndexOfOriginalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAmount");
          final Transaction _result;
          if (_cursor.moveToFirst()) {
            final int _tmpTxId;
            _tmpTxId = _cursor.getInt(_cursorIndexOfTxId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final Integer _tmpThemeId;
            if (_cursor.isNull(_cursorIndexOfThemeId)) {
              _tmpThemeId = null;
            } else {
              _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final boolean _tmpIsEdited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEdited);
            _tmpIsEdited = _tmp != 0;
            final boolean _tmpIsDutchPay;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDutchPay);
            _tmpIsDutchPay = _tmp_1 != 0;
            final Integer _tmpOriginalAmount;
            if (_cursor.isNull(_cursorIndexOfOriginalAmount)) {
              _tmpOriginalAmount = null;
            } else {
              _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
            }
            _result = new Transaction(_tmpTxId,_tmpUserId,_tmpAmount,_tmpDate,_tmpMerchant,_tmpCategoryId,_tmpThemeId,_tmpMemo,_tmpIsEdited,_tmpIsDutchPay,_tmpOriginalAmount);
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
  public Object getByCategoryAndMonth(final int userId, final int categoryId, final long startMs,
      final long endMs, final Continuation<? super List<Transaction>> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE userId = ? AND categoryId = ? AND date >= ? AND date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, categoryId);
    _argIndex = 3;
    _statement.bindLong(_argIndex, startMs);
    _argIndex = 4;
    _statement.bindLong(_argIndex, endMs);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTxId = CursorUtil.getColumnIndexOrThrow(_cursor, "txId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfIsEdited = CursorUtil.getColumnIndexOrThrow(_cursor, "isEdited");
          final int _cursorIndexOfIsDutchPay = CursorUtil.getColumnIndexOrThrow(_cursor, "isDutchPay");
          final int _cursorIndexOfOriginalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAmount");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpTxId;
            _tmpTxId = _cursor.getInt(_cursorIndexOfTxId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final Integer _tmpThemeId;
            if (_cursor.isNull(_cursorIndexOfThemeId)) {
              _tmpThemeId = null;
            } else {
              _tmpThemeId = _cursor.getInt(_cursorIndexOfThemeId);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final boolean _tmpIsEdited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEdited);
            _tmpIsEdited = _tmp != 0;
            final boolean _tmpIsDutchPay;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDutchPay);
            _tmpIsDutchPay = _tmp_1 != 0;
            final Integer _tmpOriginalAmount;
            if (_cursor.isNull(_cursorIndexOfOriginalAmount)) {
              _tmpOriginalAmount = null;
            } else {
              _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
            }
            _item = new Transaction(_tmpTxId,_tmpUserId,_tmpAmount,_tmpDate,_tmpMerchant,_tmpCategoryId,_tmpThemeId,_tmpMemo,_tmpIsEdited,_tmpIsDutchPay,_tmpOriginalAmount);
            _result.add(_item);
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

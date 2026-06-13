package com.bareunjigap.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bareunjigap.app.data.entity.Transaction

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction): Long

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE userId = :userId ORDER BY date DESC")
    fun getAllByUser(userId: Int): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE userId = :userId ORDER BY date DESC")
    suspend fun getAllByUserSync(userId: Int): List<Transaction>

    @Query("SELECT * FROM transactions WHERE userId = :userId AND date >= :startMs AND date <= :endMs ORDER BY date DESC")
    fun getByMonth(userId: Int, startMs: Long, endMs: Long): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE userId = :userId AND date >= :startMs AND date <= :endMs ORDER BY date DESC")
    suspend fun getByMonthSync(userId: Int, startMs: Long, endMs: Long): List<Transaction>

    @Query("SELECT SUM(amount) FROM transactions WHERE userId = :userId AND amount < 0 AND date >= :startMs AND date <= :endMs")
    suspend fun getTotalExpenseByMonth(userId: Int, startMs: Long, endMs: Long): Int?

    @Query("SELECT * FROM transactions WHERE themeId = :themeId")
    fun getByTheme(themeId: Int): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE txId = :txId LIMIT 1")
    suspend fun getById(txId: Int): Transaction?

    @Query("SELECT * FROM transactions WHERE userId = :userId AND categoryId = :categoryId AND date >= :startMs AND date <= :endMs")
    suspend fun getByCategoryAndMonth(userId: Int, categoryId: Int, startMs: Long, endMs: Long): List<Transaction>
}

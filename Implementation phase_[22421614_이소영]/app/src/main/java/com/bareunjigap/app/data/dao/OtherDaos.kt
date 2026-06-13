package com.bareunjigap.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bareunjigap.app.data.entity.*

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(categories: List<Category>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category): Long

    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<Category>

    @Query("SELECT * FROM categories WHERE categoryId = :id LIMIT 1")
    suspend fun findById(id: Int): Category?
}

@Dao
interface ThemeGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(theme: ThemeGroup): Long

    @Update
    suspend fun update(theme: ThemeGroup)

    @Delete
    suspend fun delete(theme: ThemeGroup)

    @Query("SELECT * FROM theme_groups WHERE userId = :userId ORDER BY createdAt DESC")
    fun getAllByUser(userId: Int): LiveData<List<ThemeGroup>>

    @Query("SELECT * FROM theme_groups WHERE themeId = :id LIMIT 1")
    suspend fun findById(id: Int): ThemeGroup?
}

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(budget: Budget): Long

    @Update
    suspend fun update(budget: Budget)

    @Query("SELECT * FROM budgets WHERE userId = :userId AND yearMonth = :yearMonth LIMIT 1")
    suspend fun getByMonth(userId: Int, yearMonth: String): Budget?

    @Query("SELECT * FROM budgets WHERE userId = :userId ORDER BY yearMonth DESC LIMIT 1")
    suspend fun getLatest(userId: Int): Budget?
}

@Dao
interface FixedScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schedule: FixedSchedule): Long

    @Update
    suspend fun update(schedule: FixedSchedule)

    @Delete
    suspend fun delete(schedule: FixedSchedule)

    @Query("SELECT * FROM fixed_schedules WHERE userId = :userId")
    fun getAllByUser(userId: Int): LiveData<List<FixedSchedule>>

    @Query("SELECT * FROM fixed_schedules WHERE userId = :userId")
    suspend fun getAllByUserSync(userId: Int): List<FixedSchedule>

    @Query("SELECT * FROM fixed_schedules WHERE dayOfMonth = :day")
    suspend fun getByDay(day: Int): List<FixedSchedule>
}

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: NotificationEntity): Long

    @Query("SELECT * FROM notifications WHERE userId = :userId ORDER BY createdAt DESC")
    fun getAllByUser(userId: Int): LiveData<List<NotificationEntity>>

    @Query("UPDATE notifications SET isRead = 1 WHERE notiId = :id")
    suspend fun markAsRead(id: Int)

    @Query("SELECT COUNT(*) FROM notifications WHERE userId = :userId AND isRead = 0")
    fun getUnreadCount(userId: Int): LiveData<Int>
}

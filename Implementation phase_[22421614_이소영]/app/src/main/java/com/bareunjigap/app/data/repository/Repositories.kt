package com.bareunjigap.app.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.bareunjigap.app.data.AppDatabase
import com.bareunjigap.app.data.entity.*
import java.security.MessageDigest

class UserRepository(context: Context) {
    private val userDao = AppDatabase.getDatabase(context).userDao()

    suspend fun register(name: String, email: String, password: String): Result<User> {
        val existing = userDao.findByEmail(email)
        if (existing != null) return Result.failure(Exception("이미 사용 중인 이메일입니다."))
        val user = User(name = name, email = email, password = password.sha256())
        val id = userDao.insert(user)
        return Result.success(user.copy(userId = id.toInt()))
    }

    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password.sha256())
    }

    suspend fun findById(userId: Int): User? = userDao.findById(userId)

    private fun String.sha256(): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}

class TransactionRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).transactionDao()

    suspend fun insert(transaction: Transaction) = dao.insert(transaction)
    suspend fun update(transaction: Transaction) = dao.update(transaction)
    suspend fun delete(transaction: Transaction) = dao.delete(transaction)

    fun getAllByUser(userId: Int): LiveData<List<Transaction>> = dao.getAllByUser(userId)
    fun getByMonth(userId: Int, startMs: Long, endMs: Long) = dao.getByMonth(userId, startMs, endMs)
    suspend fun getByMonthSync(userId: Int, startMs: Long, endMs: Long) = dao.getByMonthSync(userId, startMs, endMs)
    suspend fun getAllByUserSync(userId: Int) = dao.getAllByUserSync(userId)
    suspend fun getTotalExpenseByMonth(userId: Int, startMs: Long, endMs: Long) = dao.getTotalExpenseByMonth(userId, startMs, endMs) ?: 0
    fun getByTheme(themeId: Int) = dao.getByTheme(themeId)
    suspend fun getById(txId: Int) = dao.getById(txId)
    suspend fun getByCategoryAndMonth(userId: Int, categoryId: Int, startMs: Long, endMs: Long) =
        dao.getByCategoryAndMonth(userId, categoryId, startMs, endMs)
}

class CategoryRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).categoryDao()
    suspend fun getAll(): List<Category> = dao.getAll()
    suspend fun findById(id: Int): Category? = dao.findById(id)
    suspend fun insert(category: Category): Long = dao.insert(category)
}

class ThemeGroupRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).themeGroupDao()
    suspend fun insert(theme: ThemeGroup) = dao.insert(theme)
    suspend fun update(theme: ThemeGroup) = dao.update(theme)
    suspend fun delete(theme: ThemeGroup) = dao.delete(theme)
    fun getAllByUser(userId: Int) = dao.getAllByUser(userId)
    suspend fun findById(id: Int) = dao.findById(id)
}

class BudgetRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).budgetDao()
    suspend fun save(budget: Budget) {
        val existing = dao.getByMonth(budget.userId, budget.yearMonth)
        if (existing != null) dao.update(budget.copy(budgetId = existing.budgetId))
        else dao.insert(budget)
    }
    suspend fun getByMonth(userId: Int, yearMonth: String) = dao.getByMonth(userId, yearMonth)
    suspend fun getLatest(userId: Int) = dao.getLatest(userId)
}

class FixedScheduleRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).fixedScheduleDao()
    suspend fun insert(schedule: FixedSchedule) = dao.insert(schedule)
    suspend fun update(schedule: FixedSchedule) = dao.update(schedule)
    suspend fun delete(schedule: FixedSchedule) = dao.delete(schedule)
    fun getAllByUser(userId: Int) = dao.getAllByUser(userId)
    suspend fun getAllByUserSync(userId: Int) = dao.getAllByUserSync(userId)
    suspend fun getByDay(day: Int) = dao.getByDay(day)
}

class NotificationRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).notificationDao()
    suspend fun insert(notification: NotificationEntity) = dao.insert(notification)
    fun getAllByUser(userId: Int) = dao.getAllByUser(userId)
    suspend fun markAsRead(id: Int) = dao.markAsRead(id)
    fun getUnreadCount(userId: Int) = dao.getUnreadCount(userId)
}

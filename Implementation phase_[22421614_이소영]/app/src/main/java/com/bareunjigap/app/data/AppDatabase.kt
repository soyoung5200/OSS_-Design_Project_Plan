package com.bareunjigap.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bareunjigap.app.data.dao.*
import com.bareunjigap.app.data.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        User::class,
        Transaction::class,
        Category::class,
        ThemeGroup::class,
        Budget::class,
        FixedSchedule::class,
        NotificationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun themeGroupDao(): ThemeGroupDao
    abstract fun budgetDao(): BudgetDao
    abstract fun fixedScheduleDao(): FixedScheduleDao
    abstract fun notificationDao(): NotificationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bareunjigap_db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // 기본 카테고리 삽입
                            INSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    database.categoryDao().insertAll(defaultCategories())
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private fun defaultCategories(): List<Category> = listOf(
            Category(1, "식비",       "🍽️", "#FF6B6B"),
            Category(2, "교통",       "🚌", "#4ECDC4"),
            Category(3, "쇼핑",       "🛍️", "#45B7D1"),
            Category(4, "문화/여가",   "🎬", "#96CEB4"),
            Category(5, "의료/건강",   "💊", "#FFEAA7"),
            Category(6, "통신",       "📱", "#DDA0DD"),
            Category(7, "주거/공과금", "🏠", "#98D8C8"),
            Category(8, "카페",       "☕", "#F4A460"),
            Category(9, "운동",       "💪", "#87CEEB"),
            Category(10, "기타",      "📌", "#C0C0C0")
        )
    }
}

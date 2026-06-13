package com.bareunjigap.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Int = 0,
    val name: String,
    val iconEmoji: String,  // 이모지 아이콘
    val colorHex: String    // 색상 코드
)

@Entity(tableName = "theme_groups")
data class ThemeGroup(
    @PrimaryKey(autoGenerate = true)
    val themeId: Int = 0,
    val userId: Int,
    val name: String,
    val targetBudget: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val budgetId: Int = 0,
    val userId: Int,
    val monthlyIncome: Int,
    val fixedExpense: Int,
    val warningThreshold: Float = 0.8f,  // 80% 기본값
    val yearMonth: String  // "YYYY-MM"
)

@Entity(tableName = "fixed_schedules")
data class FixedSchedule(
    @PrimaryKey(autoGenerate = true)
    val scheduleId: Int = 0,
    val userId: Int,
    val title: String,
    val dayOfMonth: Int,   // 매월 몇 일
    val amount: Int,
    val type: String       // "INCOME" | "EXPENSE"
)

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val notiId: Int = 0,
    val userId: Int,
    val type: String,       // "WARNING" | "REMINDER"
    val message: String,
    val isRead: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

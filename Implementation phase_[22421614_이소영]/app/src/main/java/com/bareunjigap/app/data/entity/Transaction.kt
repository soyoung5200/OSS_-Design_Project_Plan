package com.bareunjigap.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val txId: Int = 0,
    val userId: Int,
    val amount: Int,           // 금액 (양수=수입, 음수=지출)
    val date: Long,            // timestamp
    val merchant: String,      // 가맹점명
    val categoryId: Int,
    val themeId: Int? = null,  // 상위 테마 (nullable)
    val memo: String = "",
    val isEdited: Boolean = false,
    val isDutchPay: Boolean = false,
    val originalAmount: Int? = null  // 더치페이 전 원본금액
)

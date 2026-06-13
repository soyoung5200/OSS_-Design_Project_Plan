package com.bareunjigap.app.util

import com.bareunjigap.app.data.entity.Transaction
import java.util.Calendar

object DummyDataGenerator {

    fun generateDummyTransactions(userId: Int): List<Transaction> {
        val transactions = mutableListOf<Transaction>()
        val cal = Calendar.getInstance()

        // 이번 달 더미 지출 데이터
        val expenses = listOf(
            Triple("스타벅스", -5500, 8),
            Triple("GS25", -3200, 8),
            Triple("쿠팡", -32000, 7),
            Triple("지하철", -1400, 7),
            Triple("마라탕", -12000, 6),
            Triple("CGV", -14000, 5),
            Triple("올리브영", -28000, 4),
            Triple("버스", -1400, 4),
            Triple("편의점", -4500, 3),
            Triple("배달의민족", -18000, 3),
            Triple("이디야", -4100, 2),
            Triple("다이소", -8500, 2),
            Triple("지하철", -1400, 1),
            Triple("카페베네", -5200, 1),
        )

        val categoryMap = mapOf(
            "스타벅스" to 8, "이디야" to 8, "카페베네" to 8,
            "GS25" to 10, "편의점" to 10,
            "마라탕" to 1, "배달의민족" to 1,
            "CGV" to 4,
            "쿠팡" to 3, "올리브영" to 3, "다이소" to 3,
            "지하철" to 2, "버스" to 2
        )

        for ((merchant, amount, day) in expenses) {
            cal.set(Calendar.DAY_OF_MONTH, day)
            transactions.add(
                Transaction(
                    userId = userId,
                    amount = amount,
                    date = cal.timeInMillis,
                    merchant = merchant,
                    categoryId = categoryMap[merchant] ?: 10
                )
            )
        }

        // 월급 수입
        cal.set(Calendar.DAY_OF_MONTH, 1)
        transactions.add(
            Transaction(
                userId = userId,
                amount = 2500000,
                date = cal.timeInMillis,
                merchant = "급여",
                categoryId = 10,
                memo = "이번 달 월급"
            )
        )

        // 지난달 데이터 (분석용)
        cal.add(Calendar.MONTH, -1)
        val lastMonthExpenses = listOf(
            Triple("스타벅스", -6000, 15),
            Triple("GS25", -4200, 14),
            Triple("쿠팡", -45000, 10),
            Triple("마라탕", -11000, 8),
            Triple("CGV", -28000, 5),
            Triple("올리브영", -15000, 3),
        )

        for ((merchant, amount, day) in lastMonthExpenses) {
            cal.set(Calendar.DAY_OF_MONTH, day)
            transactions.add(
                Transaction(
                    userId = userId,
                    amount = amount,
                    date = cal.timeInMillis,
                    merchant = merchant,
                    categoryId = categoryMap[merchant] ?: 10
                )
            )
        }

        return transactions
    }
}

package com.bareunjigap.app.util

import java.text.NumberFormat
import java.util.Locale

object FormatUtil {
    private val formatter = NumberFormat.getNumberInstance(Locale.KOREA)

    fun formatMoney(amount: Int): String {
        return "${formatter.format(Math.abs(amount))}원"
    }

    fun formatMoneyWithSign(amount: Int): String {
        return if (amount >= 0) "+${formatter.format(amount)}원"
        else "-${formatter.format(Math.abs(amount))}원"
    }

    fun formatPercent(value: Float): String {
        return "%.1f%%".format(value * 100)
    }
}

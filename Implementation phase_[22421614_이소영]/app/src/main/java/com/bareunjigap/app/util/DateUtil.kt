package com.bareunjigap.app.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private val sdfDate = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val sdfDateTime = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA)
    private val sdfMonth = SimpleDateFormat("yyyy-MM", Locale.KOREA)
    private val sdfDisplay = SimpleDateFormat("M월 d일", Locale.KOREA)

    fun getYearMonth(timeMs: Long = System.currentTimeMillis()): String {
        return sdfMonth.format(Date(timeMs))
    }

    fun getMonthRange(yearMonth: String): Pair<Long, Long> {
        val cal = Calendar.getInstance()
        val parts = yearMonth.split("-")
        cal.set(parts[0].toInt(), parts[1].toInt() - 1, 1, 0, 0, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        val end = cal.timeInMillis
        return Pair(start, end)
    }

    fun formatDate(timeMs: Long): String = sdfDate.format(Date(timeMs))
    fun formatDateTime(timeMs: Long): String = sdfDateTime.format(Date(timeMs))
    fun formatDisplay(timeMs: Long): String = sdfDisplay.format(Date(timeMs))

    fun getCurrentYearMonth(): String = sdfMonth.format(Date())

    fun getPrevYearMonth(yearMonth: String): String {
        val parts = yearMonth.split("-")
        val cal = Calendar.getInstance()
        cal.set(parts[0].toInt(), parts[1].toInt() - 1, 1)
        cal.add(Calendar.MONTH, -1)
        return sdfMonth.format(cal.time)
    }

    fun todayDayOfMonth(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }

    fun parseDate(dateStr: String): Long {
        return try { sdfDate.parse(dateStr)?.time ?: System.currentTimeMillis() }
        catch (e: Exception) { System.currentTimeMillis() }
    }
}

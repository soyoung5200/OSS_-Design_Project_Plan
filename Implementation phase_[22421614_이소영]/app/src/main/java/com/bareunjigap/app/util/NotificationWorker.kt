package com.bareunjigap.app.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.bareunjigap.app.R
import com.bareunjigap.app.data.entity.NotificationEntity
import com.bareunjigap.app.data.repository.FixedScheduleRepository
import com.bareunjigap.app.data.repository.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.concurrent.TimeUnit

class DailyNotificationWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val session = SessionManager(context)
        if (!session.isLoggedIn()) return@withContext Result.success()

        val userId = session.getUserId()
        val today = DateUtil.todayDayOfMonth()

        val scheduleRepo = FixedScheduleRepository(context)
        val notiRepo = NotificationRepository(context)

        val todaySchedules = scheduleRepo.getByDay(today)
        for (schedule in todaySchedules) {
            if (schedule.userId != userId) continue
            val message = if (schedule.type == "INCOME")
                "${schedule.dayOfMonth}일 '${schedule.title}' 입금 확인해보세요! 💰"
            else
                "${schedule.dayOfMonth}일 '${schedule.title}' 출금 예정이에요 📌"

            sendNotification(context, "고정 일정 알림", message, schedule.scheduleId)
            notiRepo.insert(
                NotificationEntity(
                    userId = userId,
                    type = "REMINDER",
                    message = message
                )
            )
        }
        Result.success()
    }

    companion object {
        const val CHANNEL_ID = "bareunjigap_channel"
        const val CHANNEL_NAME = "바른지갑 알림"
        const val WORK_NAME = "daily_notification"

        fun sendNotification(context: Context, title: String, message: String, id: Int = 1001) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                manager.createNotificationChannel(channel)
            }

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

            manager.notify(id, notification)
        }

        fun scheduleDailyWork(context: Context) {
            val configuration = Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .build()

            try {
                WorkManager.initialize(context, configuration)
            } catch (e: Exception) {
                // 이미 초기화된 경우 무시
            }

            val now = Calendar.getInstance()
            val target = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 9)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                if (before(now)) add(Calendar.DAY_OF_MONTH, 1)
            }
            val delay = target.timeInMillis - now.timeInMillis

            val request = PeriodicWorkRequestBuilder<DailyNotificationWorker>(1, TimeUnit.DAYS)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
        }
    }
}

package com.bareunjigap.app.ui.notification

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bareunjigap.app.data.entity.NotificationEntity
import com.bareunjigap.app.databinding.ItemNotificationBinding
import com.bareunjigap.app.util.DateUtil

class NotificationAdapter(
    private var notifications: List<NotificationEntity>,
    private val onItemClick: (NotificationEntity) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noti = notifications[position]
        holder.binding.apply {
            tvMessage.text = noti.message
            tvDate.text = DateUtil.formatDateTime(noti.createdAt)
            tvType.text = if (noti.type == "WARNING") "⚠️ 예산 경고" else "📅 일정 알림"

            root.alpha = if (noti.isRead) 0.5f else 1.0f
            root.setOnClickListener { onItemClick(noti) }
        }
    }

    override fun getItemCount() = notifications.size

    fun updateData(newNotifications: List<NotificationEntity>) {
        notifications = newNotifications
        notifyDataSetChanged()
    }
}

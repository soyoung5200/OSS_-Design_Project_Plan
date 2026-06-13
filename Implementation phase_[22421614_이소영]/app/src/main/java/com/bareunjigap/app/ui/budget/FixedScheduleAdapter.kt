package com.bareunjigap.app.ui.budget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bareunjigap.app.data.entity.FixedSchedule
import com.bareunjigap.app.databinding.ItemFixedScheduleBinding
import com.bareunjigap.app.util.FormatUtil

class FixedScheduleAdapter(
    private var schedules: List<FixedSchedule>,
    private val onDeleteClick: (FixedSchedule) -> Unit
) : RecyclerView.Adapter<FixedScheduleAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFixedScheduleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFixedScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule = schedules[position]
        holder.binding.apply {
            val typeEmoji = if (schedule.type == "INCOME") "💰" else "💸"
            tvTitle.text = "$typeEmoji ${schedule.title}"
            tvDay.text = "매월 ${schedule.dayOfMonth}일"
            tvAmount.text = FormatUtil.formatMoney(schedule.amount)
            btnDelete.setOnClickListener { onDeleteClick(schedule) }
        }
    }

    override fun getItemCount() = schedules.size

    fun updateData(newSchedules: List<FixedSchedule>) {
        schedules = newSchedules
        notifyDataSetChanged()
    }
}

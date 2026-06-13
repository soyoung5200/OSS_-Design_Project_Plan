package com.bareunjigap.app.ui.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bareunjigap.app.R
import com.bareunjigap.app.databinding.ActivityFixedScheduleBinding
import com.bareunjigap.app.data.entity.FixedSchedule
import com.bareunjigap.app.data.repository.FixedScheduleRepository
import com.bareunjigap.app.util.SessionManager
import kotlinx.coroutines.launch

class FixedScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFixedScheduleBinding
    private lateinit var scheduleRepo: FixedScheduleRepository
    private lateinit var session: SessionManager
    private lateinit var adapter: FixedScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFixedScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scheduleRepo = FixedScheduleRepository(this)
        session = SessionManager(this)

        adapter = FixedScheduleAdapter(emptyList()) { schedule ->
            confirmDelete(schedule)
        }

        binding.rvSchedules.layoutManager = LinearLayoutManager(this)
        binding.rvSchedules.adapter = adapter

        binding.btnBack.setOnClickListener { finish() }
        binding.btnAddSchedule.setOnClickListener { showAddDialog() }

        loadSchedules()
    }

    private fun loadSchedules() {
        scheduleRepo.getAllByUser(session.getUserId()).observe(this) { schedules ->
            adapter.updateData(schedules)
        }
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_schedule, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.etScheduleTitle)
        val etDay = dialogView.findViewById<EditText>(R.id.etScheduleDay)
        val etAmount = dialogView.findViewById<EditText>(R.id.etScheduleAmount)
        val rgType = dialogView.findViewById<RadioGroup>(R.id.rgScheduleType)

        AlertDialog.Builder(this)
            .setTitle("고정 일정 추가")
            .setView(dialogView)
            .setPositiveButton("추가") { _, _ ->
                val title = etTitle.text.toString().trim()
                val day = etDay.text.toString().toIntOrNull()
                val amount = etAmount.text.toString().toIntOrNull() ?: 0
                val type = if (rgType.checkedRadioButtonId == R.id.rbIncome) "INCOME" else "EXPENSE"

                if (title.isEmpty() || day == null || day !in 1..31) {
                    Toast.makeText(this, "올바른 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    scheduleRepo.insert(FixedSchedule(
                        userId = session.getUserId(),
                        title = title,
                        dayOfMonth = day,
                        amount = amount,
                        type = type
                    ))
                    Toast.makeText(this@FixedScheduleActivity, "추가되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun confirmDelete(schedule: FixedSchedule) {
        AlertDialog.Builder(this)
            .setTitle("삭제")
            .setMessage("'${schedule.title}' 일정을 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                lifecycleScope.launch {
                    scheduleRepo.delete(schedule)
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }
}

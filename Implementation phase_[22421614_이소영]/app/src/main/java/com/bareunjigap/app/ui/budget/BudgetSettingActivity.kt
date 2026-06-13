package com.bareunjigap.app.ui.budget

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bareunjigap.app.databinding.ActivityBudgetSettingBinding
import com.bareunjigap.app.data.entity.Budget
import com.bareunjigap.app.data.repository.BudgetRepository
import com.bareunjigap.app.util.DateUtil
import com.bareunjigap.app.util.FormatUtil
import com.bareunjigap.app.util.SessionManager
import kotlinx.coroutines.launch

class BudgetSettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBudgetSettingBinding
    private lateinit var budgetRepo: BudgetRepository
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        budgetRepo = BudgetRepository(this)
        session = SessionManager(this)

        binding.btnBack.setOnClickListener { finish() }
        binding.btnSave.setOnClickListener { saveBudget() }
        binding.btnFixedSchedule.setOnClickListener {
            startActivity(Intent(this, FixedScheduleActivity::class.java))
        }

        loadCurrentBudget()
    }

    private fun loadCurrentBudget() {
        lifecycleScope.launch {
            val budget = budgetRepo.getLatest(session.getUserId())
            budget?.let {
                binding.etIncome.setText(it.monthlyIncome.toString())
                binding.etFixedExpense.setText(it.fixedExpense.toString())
                val threshold = (it.warningThreshold * 100).toInt()
                binding.etThreshold.setText(threshold.toString())
                updateAvailable(it.monthlyIncome, it.fixedExpense)
            }
        }
    }

    private fun saveBudget() {
        val incomeStr = binding.etIncome.text.toString()
        val fixedStr = binding.etFixedExpense.text.toString()
        val thresholdStr = binding.etThreshold.text.toString()

        if (incomeStr.isEmpty()) {
            Toast.makeText(this, "월 수입을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val income = incomeStr.toIntOrNull() ?: 0
        val fixed = fixedStr.toIntOrNull() ?: 0
        val threshold = (thresholdStr.toIntOrNull() ?: 80) / 100f

        lifecycleScope.launch {
            budgetRepo.save(Budget(
                userId = session.getUserId(),
                monthlyIncome = income,
                fixedExpense = fixed,
                warningThreshold = threshold,
                yearMonth = DateUtil.getCurrentYearMonth()
            ))
            updateAvailable(income, fixed)
            Toast.makeText(this@BudgetSettingActivity, "예산이 저장되었습니다", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateAvailable(income: Int, fixed: Int) {
        val available = income - fixed
        binding.tvAvailable.text = "가용 예산: ${FormatUtil.formatMoney(available)}"
    }
}

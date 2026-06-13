package com.bareunjigap.app.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bareunjigap.app.databinding.FragmentDashboardBinding
import com.bareunjigap.app.data.entity.Transaction
import com.bareunjigap.app.data.repository.BudgetRepository
import com.bareunjigap.app.data.repository.CategoryRepository
import com.bareunjigap.app.data.repository.NotificationRepository
import com.bareunjigap.app.data.repository.TransactionRepository
import com.bareunjigap.app.ui.budget.BudgetSettingActivity
import com.bareunjigap.app.ui.transaction.TransactionEditActivity
import com.bareunjigap.app.ui.transaction.TransactionAdapter
import com.bareunjigap.app.util.*
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var session: SessionManager
    private lateinit var txRepo: TransactionRepository
    private lateinit var budgetRepo: BudgetRepository
    private lateinit var categoryRepo: CategoryRepository
    private lateinit var notiRepo: NotificationRepository
    private lateinit var adapter: TransactionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session = SessionManager(requireContext())
        txRepo = TransactionRepository(requireContext())
        budgetRepo = BudgetRepository(requireContext())
        categoryRepo = CategoryRepository(requireContext())
        notiRepo = NotificationRepository(requireContext())

        binding.tvUserName.text = "${session.getUserName()}님의 가계부"

        // RecyclerView 설정
        adapter = TransactionAdapter(emptyList(), emptyList()) { tx ->
            val intent = Intent(requireContext(), TransactionEditActivity::class.java)
            intent.putExtra("txId", tx.txId)
            startActivity(intent)
        }
        binding.rvRecentTransactions.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecentTransactions.adapter = adapter

        // 거래 추가 버튼
        binding.fabAddTransaction.setOnClickListener {
            startActivity(Intent(requireContext(), TransactionEditActivity::class.java))
        }

        // 예산 설정 버튼
        binding.btnBudgetSetting.setOnClickListener {
            startActivity(Intent(requireContext(), BudgetSettingActivity::class.java))
        }

        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val userId = session.getUserId()
        val yearMonth = DateUtil.getCurrentYearMonth()
        val (startMs, endMs) = DateUtil.getMonthRange(yearMonth)

        lifecycleScope.launch {
            val categories = categoryRepo.getAll()

            // 이번 달 거래 내역 관찰
            txRepo.getByMonth(userId, startMs, endMs).observe(viewLifecycleOwner) { transactions ->
                lifecycleScope.launch {
                    updateDashboard(transactions, categories, userId, yearMonth, startMs, endMs)
                }
            }
        }
    }

    private suspend fun updateDashboard(
        transactions: List<Transaction>,
        categories: List<com.bareunjigap.app.data.entity.Category>,
        userId: Int,
        yearMonth: String,
        startMs: Long,
        endMs: Long
    ) {
        // 최근 5개만 표시
        val recent = transactions.sortedByDescending { it.date }.take(5)
        adapter.updateData(recent, categories)

        // 이번 달 지출 합계
        val totalExpense = transactions.filter { it.amount < 0 }.sumOf { Math.abs(it.amount) }
        val totalIncome = transactions.filter { it.amount > 0 }.sumOf { it.amount }

        binding.tvTotalExpense.text = FormatUtil.formatMoney(totalExpense)
        binding.tvTotalIncome.text = FormatUtil.formatMoney(totalIncome)

        // 예산 게이지
        val budget = budgetRepo.getLatest(userId)
        if (budget != null) {
            val available = budget.monthlyIncome - budget.fixedExpense
            val usedPercent = if (available > 0) (totalExpense.toFloat() / available * 100).toInt().coerceAtMost(100) else 0

            binding.progressBudget.progress = usedPercent
            binding.tvBudgetPercent.text = "$usedPercent%"
            binding.tvBudgetStatus.text = "가용 예산 ${FormatUtil.formatMoney(available - totalExpense)} 남음"

            // 경고 알림 (80% 초과 시)
            if (available > 0 && totalExpense.toFloat() / available >= budget.warningThreshold) {
                binding.cardWarning.visibility = View.VISIBLE
                binding.tvWarningMsg.text = "⚠️ 이번 달 예산의 ${(usedPercent)}%를 사용했어요!"

                // DB에 알림 저장 (중복 방지 필요 시 추가 로직 가능)
                notiRepo.insert(
                    com.bareunjigap.app.data.entity.NotificationEntity(
                        userId = userId,
                        type = "WARNING",
                        message = "이번 달 예산의 $usedPercent%를 사용했습니다. 지출을 줄여보세요!"
                    )
                )
            } else {
                binding.cardWarning.visibility = View.GONE
            }
        } else {
            binding.progressBudget.progress = 0
            binding.tvBudgetPercent.text = "0%"
            binding.tvBudgetStatus.text = "예산을 설정해주세요"
        }

        binding.tvYearMonth.text = yearMonth.replace("-", "년 ") + "월"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

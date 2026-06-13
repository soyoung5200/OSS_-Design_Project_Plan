package com.bareunjigap.app.ui.analysis

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bareunjigap.app.databinding.FragmentAnalysisBinding
import com.bareunjigap.app.data.repository.CategoryRepository
import com.bareunjigap.app.data.repository.TransactionRepository
import com.bareunjigap.app.util.DateUtil
import com.bareunjigap.app.util.FormatUtil
import com.bareunjigap.app.util.SessionManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.coroutines.launch

class AnalysisFragment : Fragment() {

    private var _binding: FragmentAnalysisBinding? = null
    private val binding get() = _binding!!

    private lateinit var session: SessionManager
    private lateinit var txRepo: TransactionRepository
    private lateinit var categoryRepo: CategoryRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session = SessionManager(requireContext())
        txRepo = TransactionRepository(requireContext())
        categoryRepo = CategoryRepository(requireContext())

        setupPieChart()
        loadData()
    }

    private fun setupPieChart() {
        binding.pieChart.apply {
            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 45f
            setHoleColor(Color.WHITE)
            transparentCircleRadius = 50f
            setUsePercentValues(true)
            legend.isEnabled = true
            setEntryLabelTextSize(12f)
            setEntryLabelColor(Color.DKGRAY)
            animateY(800)
        }
    }

    private fun loadData() {
        val userId = session.getUserId()
        val yearMonth = DateUtil.getCurrentYearMonth()
        val prevYearMonth = DateUtil.getPrevYearMonth(yearMonth)
        val (startMs, endMs) = DateUtil.getMonthRange(yearMonth)
        val (prevStartMs, prevEndMs) = DateUtil.getMonthRange(prevYearMonth)

        lifecycleScope.launch {
            val categories = categoryRepo.getAll()
            val thisMonthTx = txRepo.getByMonthSync(userId, startMs, endMs).filter { it.amount < 0 }
            val prevMonthTx = txRepo.getByMonthSync(userId, prevStartMs, prevEndMs).filter { it.amount < 0 }

            if (thisMonthTx.isEmpty()) {
                binding.tvNoData.visibility = View.VISIBLE
                binding.pieChart.visibility = View.GONE
                return@launch
            }

            binding.tvNoData.visibility = View.GONE
            binding.pieChart.visibility = View.VISIBLE

            // 카테고리별 지출 집계
            val categoryExpenses = mutableMapOf<Int, Int>()
            for (tx in thisMonthTx) {
                categoryExpenses[tx.categoryId] = (categoryExpenses[tx.categoryId] ?: 0) + Math.abs(tx.amount)
            }

            val totalThisMonth = thisMonthTx.sumOf { Math.abs(it.amount) }
            val totalPrevMonth = prevMonthTx.sumOf { Math.abs(it.amount) }

            // 파이차트 엔트리 생성
            val entries = mutableListOf<PieEntry>()
            val colors = mutableListOf<Int>()

            categoryExpenses.entries.sortedByDescending { it.value }.forEach { (catId, amount) ->
                val cat = categories.find { it.categoryId == catId }
                val name = cat?.name ?: "기타"
                entries.add(PieEntry(amount.toFloat(), name))
                val colorInt = try {
                    Color.parseColor(cat?.colorHex ?: "#C0C0C0")
                } catch (e: Exception) { Color.LTGRAY }
                colors.add(colorInt)
            }

            val dataSet = PieDataSet(entries, "").apply {
                this.colors = colors
                valueTextSize = 12f
                valueTextColor = Color.DKGRAY
                valueFormatter = PercentFormatter(binding.pieChart)
                sliceSpace = 3f
            }

            binding.pieChart.data = PieData(dataSet)
            binding.pieChart.invalidate()

            // 이번 달 요약
            binding.tvMonthSummary.text = "${yearMonth.replace("-", "년 ")}월 총 지출: ${FormatUtil.formatMoney(totalThisMonth)}"

            // 전월 대비 코멘트
            val comment = generateComment(categoryExpenses, categories, totalThisMonth, totalPrevMonth)
            binding.tvAnalysisComment.text = comment

            // 카테고리별 상세 리스트
            val details = StringBuilder()
            categoryExpenses.entries.sortedByDescending { it.value }.forEach { (catId, amount) ->
                val cat = categories.find { it.categoryId == catId }
                val percent = if (totalThisMonth > 0) amount.toFloat() / totalThisMonth * 100 else 0f
                details.append("${cat?.iconEmoji ?: "📌"} ${cat?.name ?: "기타"}  ${FormatUtil.formatMoney(amount)}  (${String.format("%.1f", percent)}%)\n")
            }
            binding.tvCategoryDetails.text = details.toString().trimEnd()
        }
    }

    private fun generateComment(
        expenses: Map<Int, Int>,
        categories: List<com.bareunjigap.app.data.entity.Category>,
        thisMonth: Int,
        prevMonth: Int
    ): String {
        val sb = StringBuilder()

        if (prevMonth > 0) {
            val diff = thisMonth - prevMonth
            val pct = Math.abs(diff).toFloat() / prevMonth * 100
            if (diff > 0) sb.append("📈 지난달보다 지출이 ${String.format("%.1f", pct)}% 늘었어요.\n")
            else sb.append("📉 지난달보다 지출이 ${String.format("%.1f", pct)}% 줄었어요. 잘하고 있어요!\n")
        }

        // 가장 많이 쓴 카테고리
        val top = expenses.entries.maxByOrNull { it.value }
        top?.let {
            val cat = categories.find { c -> c.categoryId == it.key }
            sb.append("💡 이번 달 가장 많이 쓴 분야는 '${cat?.name ?: "기타'"}' (${FormatUtil.formatMoney(it.value)})이에요.")
        }

        return sb.toString().ifEmpty { "📊 지출 데이터를 분석했어요!" }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.bareunjigap.app.ui.transaction

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bareunjigap.app.databinding.FragmentTransactionBinding
import com.bareunjigap.app.data.repository.CategoryRepository
import com.bareunjigap.app.data.repository.TransactionRepository
import com.bareunjigap.app.util.DateUtil
import com.bareunjigap.app.util.SessionManager
import kotlinx.coroutines.launch
import java.util.Calendar

class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!

    private lateinit var session: SessionManager
    private lateinit var txRepo: TransactionRepository
    private lateinit var categoryRepo: CategoryRepository
    private lateinit var adapter: TransactionAdapter

    private var currentYearMonth = DateUtil.getCurrentYearMonth()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session = SessionManager(requireContext())
        txRepo = TransactionRepository(requireContext())
        categoryRepo = CategoryRepository(requireContext())

        adapter = TransactionAdapter(emptyList(), emptyList()) { tx ->
            val intent = Intent(requireContext(), TransactionEditActivity::class.java)
            intent.putExtra("txId", tx.txId)
            startActivity(intent)
        }

        binding.rvTransactions.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransactions.adapter = adapter

        binding.fabAddTransaction.setOnClickListener {
            startActivity(Intent(requireContext(), TransactionEditActivity::class.java))
        }

        // 월 이동 버튼
        binding.btnPrevMonth.setOnClickListener {
            currentYearMonth = DateUtil.getPrevYearMonth(currentYearMonth)
            loadData()
        }
        binding.btnNextMonth.setOnClickListener {
            val next = getNextYearMonth(currentYearMonth)
            val now = DateUtil.getCurrentYearMonth()
            if (next <= now) {
                currentYearMonth = next
                loadData()
            }
        }

        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val userId = session.getUserId()
        val (startMs, endMs) = DateUtil.getMonthRange(currentYearMonth)
        binding.tvYearMonth.text = currentYearMonth.replace("-", "년 ") + "월"

        lifecycleScope.launch {
            val categories = categoryRepo.getAll()
            txRepo.getByMonth(userId, startMs, endMs).observe(viewLifecycleOwner) { transactions ->
                val sorted = transactions.sortedByDescending { it.date }
                adapter.updateData(sorted, categories)

                val totalExpense = sorted.filter { it.amount < 0 }.sumOf { Math.abs(it.amount) }
                val totalIncome = sorted.filter { it.amount > 0 }.sumOf { it.amount }
                binding.tvSummary.text = "수입 +${com.bareunjigap.app.util.FormatUtil.formatMoney(totalIncome)}  지출 -${com.bareunjigap.app.util.FormatUtil.formatMoney(totalExpense)}"

                if (sorted.isEmpty()) {
                    binding.tvEmpty.visibility = View.VISIBLE
                    binding.rvTransactions.visibility = View.GONE
                } else {
                    binding.tvEmpty.visibility = View.GONE
                    binding.rvTransactions.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getNextYearMonth(yearMonth: String): String {
        val parts = yearMonth.split("-")
        val cal = Calendar.getInstance()
        cal.set(parts[0].toInt(), parts[1].toInt() - 1, 1)
        cal.add(Calendar.MONTH, 1)
        return DateUtil.getYearMonth(cal.timeInMillis)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

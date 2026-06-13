package com.bareunjigap.app.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bareunjigap.app.databinding.FragmentThemeBinding
import com.bareunjigap.app.data.entity.ThemeGroup
import com.bareunjigap.app.data.repository.ThemeGroupRepository
import com.bareunjigap.app.data.repository.TransactionRepository
import com.bareunjigap.app.data.repository.CategoryRepository
import com.bareunjigap.app.util.FormatUtil
import com.bareunjigap.app.util.DateUtil
import com.bareunjigap.app.util.SessionManager
import kotlinx.coroutines.launch

class ThemeFragment : Fragment() {

    private var _binding: FragmentThemeBinding? = null
    private val binding get() = _binding!!

    private lateinit var session: SessionManager
    private lateinit var themeRepo: ThemeGroupRepository
    private lateinit var txRepo: TransactionRepository
    private lateinit var categoryRepo: CategoryRepository
    private lateinit var adapter: ThemeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session = SessionManager(requireContext())
        themeRepo = ThemeGroupRepository(requireContext())
        txRepo = TransactionRepository(requireContext())
        categoryRepo = CategoryRepository(requireContext())

        adapter = ThemeAdapter(emptyList(),
            onItemClick = { theme -> showThemeTransactionPicker(theme) },
            onDeleteClick = { theme -> confirmDeleteTheme(theme) }
        )

        binding.rvThemes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvThemes.adapter = adapter

        binding.fabAddTheme.setOnClickListener { showAddThemeDialog() }

        loadThemes()
    }

    private fun loadThemes() {
        val userId = session.getUserId()
        themeRepo.getAllByUser(userId).observe(viewLifecycleOwner) { themes ->
            adapter.updateData(themes)
            if (themes.isEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvThemes.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.rvThemes.visibility = View.VISIBLE
            }
        }
    }

    private fun showThemeTransactionPicker(theme: ThemeGroup) {
        lifecycleScope.launch {
            val userId = session.getUserId()
            val allTx = txRepo.getAllByUserSync(userId).filter { it.amount < 0 }
            val categories = categoryRepo.getAll()

            if (allTx.isEmpty()) {
                AlertDialog.Builder(requireContext())
                    .setTitle("📁 ${theme.name}")
                    .setMessage("지출 내역이 없어요. 먼저 거래를 추가해주세요!")
                    .setPositiveButton("확인", null)
                    .show()
                return@launch
            }

            // 현재 이 테마에 속한 txId 목록
            val alreadyInTheme = allTx.filter { it.themeId == theme.themeId }.map { it.txId }.toMutableSet()

            // 체크박스 항목 목록
            val labels = allTx.map { tx ->
                val cat = categories.find { it.categoryId == tx.categoryId }
                "${DateUtil.formatDisplay(tx.date)} ${tx.merchant} ${FormatUtil.formatMoney(Math.abs(tx.amount))}"
            }.toTypedArray()

            val checked = allTx.map { it.txId in alreadyInTheme }.toBooleanArray()

            AlertDialog.Builder(requireContext())
                .setTitle("📁 ${theme.name}\n포함할 지출 선택")
                .setMultiChoiceItems(labels, checked) { _, which, isChecked ->
                    val txId = allTx[which].txId
                    if (isChecked) alreadyInTheme.add(txId)
                    else alreadyInTheme.remove(txId)
                }
                .setPositiveButton("저장") { _, _ ->
                    lifecycleScope.launch {
                        // 선택한 것들은 이 테마로, 선택 해제한 것들은 테마 null로
                        allTx.forEach { tx ->
                            if (tx.txId in alreadyInTheme) {
                                txRepo.update(tx.copy(themeId = theme.themeId))
                            } else if (tx.themeId == theme.themeId) {
                                txRepo.update(tx.copy(themeId = null))
                            }
                        }
                        // 총 지출 합계
                        val total = allTx.filter { it.txId in alreadyInTheme }.sumOf { Math.abs(it.amount) }
                        Toast.makeText(requireContext(),
                            "저장되었습니다! 테마 총 지출: ${FormatUtil.formatMoney(total)}",
                            Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton("취소", null)
                .show()
        }
    }

    private fun showAddThemeDialog() {
        val etName = EditText(requireContext()).apply { hint = "테마 이름 (예: 제주 여행)" }
        val etBudget = EditText(requireContext()).apply {
            hint = "목표 예산 (선택)"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }

        val layout = android.widget.LinearLayout(requireContext()).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(48, 24, 48, 0)
            addView(etName)
            addView(etBudget)
        }

        AlertDialog.Builder(requireContext())
            .setTitle("새 테마 만들기")
            .setView(layout)
            .setPositiveButton("만들기") { _, _ ->
                val name = etName.text.toString().trim()
                if (name.isEmpty()) {
                    Toast.makeText(requireContext(), "테마 이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val budget = etBudget.text.toString().toIntOrNull() ?: 0
                lifecycleScope.launch {
                    themeRepo.insert(ThemeGroup(
                        userId = session.getUserId(),
                        name = name,
                        targetBudget = budget
                    ))
                    Toast.makeText(requireContext(), "테마가 생성되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun confirmDeleteTheme(theme: ThemeGroup) {
        AlertDialog.Builder(requireContext())
            .setTitle("테마 삭제")
            .setMessage("'${theme.name}' 테마를 삭제하시겠습니까?\n(거래 내역은 유지됩니다)")
            .setPositiveButton("삭제") { _, _ ->
                lifecycleScope.launch {
                    themeRepo.delete(theme)
                    Toast.makeText(requireContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

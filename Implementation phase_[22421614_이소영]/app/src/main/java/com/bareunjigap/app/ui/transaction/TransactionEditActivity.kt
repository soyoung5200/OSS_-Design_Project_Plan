package com.bareunjigap.app.ui.transaction

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bareunjigap.app.databinding.ActivityTransactionEditBinding
import com.bareunjigap.app.data.entity.Category
import com.bareunjigap.app.data.entity.ThemeGroup
import com.bareunjigap.app.data.entity.Transaction
import com.bareunjigap.app.data.repository.CategoryRepository
import com.bareunjigap.app.data.repository.ThemeGroupRepository
import com.bareunjigap.app.data.repository.TransactionRepository
import com.bareunjigap.app.ui.dutchpay.DutchPayActivity
import com.bareunjigap.app.util.DateUtil
import com.bareunjigap.app.util.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class TransactionEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionEditBinding
    private lateinit var txRepo: TransactionRepository
    private lateinit var categoryRepo: CategoryRepository
    private lateinit var themeRepo: ThemeGroupRepository
    private lateinit var session: SessionManager

    private var txId: Int = -1
    private var existingTx: Transaction? = null
    private var selectedDateMs: Long = System.currentTimeMillis()
    private var categoryList = mutableListOf<Category>()
    private var themeList = listOf<ThemeGroup>()
    private var isExpense = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txRepo = TransactionRepository(this)
        categoryRepo = CategoryRepository(this)
        themeRepo = ThemeGroupRepository(this)
        session = SessionManager(this)

        txId = intent.getIntExtra("txId", -1)

        setupUI()
        loadCategories()
        loadThemes()
    }

    override fun onResume() {
        super.onResume()
        // 더치페이 후 돌아왔을 때 최신 데이터 다시 불러오기
        if (txId != -1) {
            lifecycleScope.launch {
                val tx = withContext(Dispatchers.IO) { txRepo.getById(txId) }
                tx?.let {
                    existingTx = it
                    binding.etMerchant.setText(it.merchant)
                    binding.etAmount.setText(Math.abs(it.amount).toString())
                    binding.etMemo.setText(it.memo)
                    selectedDateMs = it.date
                    binding.tvDate.text = DateUtil.formatDate(it.date)
                    isExpense = it.amount < 0
                    updateToggleStyle(isExpense)
                    binding.tvTitle.text = "거래 수정"
                }
            }
        }
    }

    private fun setupUI() {
        updateToggleStyle(true)

        binding.btnExpense.setOnClickListener {
            isExpense = true
            updateToggleStyle(true)
        }
        binding.btnIncome.setOnClickListener {
            isExpense = false
            updateToggleStyle(false)
        }

        binding.tvDate.text = DateUtil.formatDate(selectedDateMs)
        binding.tvDate.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(this,
                { _, y, m, d ->
                    cal.set(y, m, d)
                    selectedDateMs = cal.timeInMillis
                    binding.tvDate.text = DateUtil.formatDate(selectedDateMs)
                },
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnAddCategory.setOnClickListener { showAddCategoryDialog() }
        binding.btnSave.setOnClickListener { saveTransaction() }
        binding.btnBack.setOnClickListener { finish() }

        if (txId != -1) {
            binding.btnDelete.visibility = View.VISIBLE
            binding.btnDutchPay.visibility = View.VISIBLE
            binding.btnDelete.setOnClickListener { confirmDelete() }
            binding.btnDutchPay.setOnClickListener {
                val intent = Intent(this, DutchPayActivity::class.java)
                intent.putExtra("txId", txId)
                startActivity(intent)
            }
        }
    }

    private fun updateToggleStyle(expense: Boolean) {
        if (expense) {
            binding.btnExpense.setBackgroundColor(Color.parseColor("#2563EB"))
            binding.btnExpense.setTextColor(Color.WHITE)
            binding.btnIncome.setBackgroundColor(Color.WHITE)
            binding.btnIncome.setTextColor(Color.parseColor("#2563EB"))
        } else {
            binding.btnIncome.setBackgroundColor(Color.parseColor("#2563EB"))
            binding.btnIncome.setTextColor(Color.WHITE)
            binding.btnExpense.setBackgroundColor(Color.WHITE)
            binding.btnExpense.setTextColor(Color.parseColor("#2563EB"))
        }
    }

    private fun loadCategories() {
        lifecycleScope.launch {
            categoryList.clear()
            categoryList.addAll(withContext(Dispatchers.IO) { categoryRepo.getAll() })
            refreshCategorySpinner()
        }
    }

    private fun refreshCategorySpinner() {
        val names = categoryList.map { "${it.iconEmoji} ${it.name}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, names)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter
    }

    private fun showAddCategoryDialog() {
        val etName = EditText(this).apply { hint = "카테고리 이름" }
        val etEmoji = EditText(this).apply { hint = "이모지 (예: 🎮)" }

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(48, 24, 48, 0)
            addView(etName)
            addView(etEmoji)
        }

        AlertDialog.Builder(this)
            .setTitle("카테고리 추가")
            .setView(layout)
            .setPositiveButton("추가") { _, _ ->
                val name = etName.text.toString().trim()
                val emoji = etEmoji.text.toString().trim().ifEmpty { "📌" }
                if (name.isEmpty()) {
                    Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                lifecycleScope.launch {
                    val colors = listOf("#FF6B6B","#4ECDC4","#45B7D1","#96CEB4","#DDA0DD","#F4A460","#87CEEB")
                    val newCat = Category(name = name, iconEmoji = emoji, colorHex = colors.random())
                    withContext(Dispatchers.IO) { categoryRepo.insert(newCat) }
                    loadCategories()
                    Toast.makeText(this@TransactionEditActivity, "카테고리가 추가되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun loadThemes() {
        themeRepo.getAllByUser(session.getUserId()).observe(this) { themes ->
            themeList = themes
            val names = mutableListOf("테마 없음") + themes.map { "📁 ${it.name}" }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, names)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerTheme.adapter = adapter
        }
    }

    private fun saveTransaction() {
        val merchant = binding.etMerchant.text.toString().trim()
        val amountStr = binding.etAmount.text.toString()
        val memo = binding.etMemo.text.toString().trim()

        if (merchant.isEmpty()) {
            Toast.makeText(this, "가맹점/항목명을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "금액을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountStr.toIntOrNull() ?: run {
            Toast.makeText(this, "올바른 금액을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedCategoryId = if (categoryList.isEmpty()) 10
        else categoryList[binding.spinnerCategory.selectedItemPosition].categoryId

        val selectedThemeId = if (binding.spinnerTheme.selectedItemPosition == 0) null
        else themeList.getOrNull(binding.spinnerTheme.selectedItemPosition - 1)?.themeId

        val finalAmount = if (isExpense) -amount else amount

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                if (existingTx != null) {
                    txRepo.update(existingTx!!.copy(
                        amount = finalAmount,
                        merchant = merchant,
                        memo = memo,
                        date = selectedDateMs,
                        categoryId = selectedCategoryId,
                        themeId = selectedThemeId,
                        isEdited = true
                    ))
                } else {
                    txRepo.insert(Transaction(
                        userId = session.getUserId(),
                        amount = finalAmount,
                        merchant = merchant,
                        memo = memo,
                        date = selectedDateMs,
                        categoryId = selectedCategoryId,
                        themeId = selectedThemeId
                    ))
                }
            }
            Toast.makeText(this@TransactionEditActivity,
                if (existingTx != null) "수정되었습니다" else "저장되었습니다",
                Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun confirmDelete() {
        AlertDialog.Builder(this)
            .setTitle("삭제 확인")
            .setMessage("이 거래 내역을 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { existingTx?.let { txRepo.delete(it) } }
                    Toast.makeText(this@TransactionEditActivity, "삭제되었습니다", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }
}

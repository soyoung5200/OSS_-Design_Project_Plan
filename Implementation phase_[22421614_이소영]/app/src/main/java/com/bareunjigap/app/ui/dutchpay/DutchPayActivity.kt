package com.bareunjigap.app.ui.dutchpay

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bareunjigap.app.databinding.ActivityDutchpayBinding
import com.bareunjigap.app.data.entity.Transaction
import com.bareunjigap.app.data.repository.TransactionRepository
import com.bareunjigap.app.util.FormatUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DutchPayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDutchpayBinding
    private lateinit var txRepo: TransactionRepository
    private var txId: Int = -1
    private var originalAmount: Int = 0
    private var calculatedShare: Int = 0
    private var currentTx: Transaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDutchpayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txRepo = TransactionRepository(this)
        txId = intent.getIntExtra("txId", -1)

        binding.btnBack.setOnClickListener { finish() }
        binding.btnApply.isEnabled = false

        if (txId != -1) loadTransaction()

        binding.btnCalculate.setOnClickListener { calculate() }
        binding.btnApply.setOnClickListener { applyDutchPay() }
    }

    private fun loadTransaction() {
        lifecycleScope.launch {
            val tx = withContext(Dispatchers.IO) { txRepo.getById(txId) }
            tx?.let {
                currentTx = it
                // 이미 더치페이 적용된 경우 원본 금액 사용
                originalAmount = if (it.isDutchPay && it.originalAmount != null)
                    it.originalAmount else Math.abs(it.amount)
                binding.tvTotalAmount.text = "총 결제 금액: ${FormatUtil.formatMoney(originalAmount)}"
                binding.tvMerchant.text = it.merchant
            }
        }
    }

    private fun calculate() {
        val countStr = binding.etHeadCount.text.toString()
        val count = countStr.toIntOrNull()

        if (count == null || count < 2) {
            Toast.makeText(this, "2명 이상 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        calculatedShare = originalAmount / count
        val remainder = originalAmount % count

        binding.tvMyShare.text = "내 부담 금액: ${FormatUtil.formatMoney(calculatedShare)}"
        binding.tvRemainder.text = if (remainder > 0)
            "나머지 ${remainder}원은 직접 조정해주세요" else ""
        binding.btnApply.isEnabled = true
    }

    private fun applyDutchPay() {
        if (calculatedShare <= 0) return
        val tx = currentTx ?: return

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                txRepo.update(
                    tx.copy(
                        amount = -calculatedShare,
                        originalAmount = originalAmount,
                        isDutchPay = true,
                        isEdited = true
                    )
                )
            }
            Toast.makeText(
                this@DutchPayActivity,
                "${FormatUtil.formatMoney(calculatedShare)}으로 지출 반영되었습니다!",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }
}

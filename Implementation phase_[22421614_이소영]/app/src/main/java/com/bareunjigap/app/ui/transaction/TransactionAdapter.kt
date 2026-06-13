package com.bareunjigap.app.ui.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bareunjigap.app.R
import com.bareunjigap.app.data.entity.Category
import com.bareunjigap.app.data.entity.Transaction
import com.bareunjigap.app.databinding.ItemTransactionBinding
import com.bareunjigap.app.util.DateUtil
import com.bareunjigap.app.util.FormatUtil

class TransactionAdapter(
    private var transactions: List<Transaction>,
    private var categories: List<Category>,
    private val onItemClick: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tx = transactions[position]
        val category = categories.find { it.categoryId == tx.categoryId }

        holder.binding.apply {
            tvMerchant.text = tx.merchant
            tvDate.text = DateUtil.formatDisplay(tx.date)
            tvCategory.text = category?.let { "${it.iconEmoji} ${it.name}" } ?: "기타"

            val amountText = FormatUtil.formatMoneyWithSign(tx.amount)
            tvAmount.text = amountText
            tvAmount.setTextColor(
                ContextCompat.getColor(
                    root.context,
                    if (tx.amount >= 0) R.color.income_green else R.color.expense_red
                )
            )

            if (tx.isDutchPay) {
                tvDutchBadge.visibility = android.view.View.VISIBLE
            } else {
                tvDutchBadge.visibility = android.view.View.GONE
            }

            if (tx.memo.isNotEmpty()) {
                tvMemo.visibility = android.view.View.VISIBLE
                tvMemo.text = tx.memo
            } else {
                tvMemo.visibility = android.view.View.GONE
            }

            root.setOnClickListener { onItemClick(tx) }
        }
    }

    override fun getItemCount() = transactions.size

    fun updateData(newTransactions: List<Transaction>, newCategories: List<Category>) {
        transactions = newTransactions
        categories = newCategories
        notifyDataSetChanged()
    }
}

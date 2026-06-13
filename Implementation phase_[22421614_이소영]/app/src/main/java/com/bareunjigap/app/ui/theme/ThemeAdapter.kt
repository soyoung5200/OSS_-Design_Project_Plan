package com.bareunjigap.app.ui.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bareunjigap.app.data.entity.ThemeGroup
import com.bareunjigap.app.databinding.ItemThemeBinding
import com.bareunjigap.app.util.FormatUtil

class ThemeAdapter(
    private var themes: List<ThemeGroup>,
    private val onItemClick: (ThemeGroup) -> Unit,
    private val onDeleteClick: (ThemeGroup) -> Unit
) : RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemThemeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val theme = themes[position]
        holder.binding.apply {
            tvThemeName.text = "📁 ${theme.name}"
            tvTargetBudget.text = if (theme.targetBudget > 0)
                "목표: ${FormatUtil.formatMoney(theme.targetBudget)}"
            else "목표 예산 없음"
            root.setOnClickListener { onItemClick(theme) }
            btnDelete.setOnClickListener { onDeleteClick(theme) }
        }
    }

    override fun getItemCount() = themes.size

    fun updateData(newThemes: List<ThemeGroup>) {
        themes = newThemes
        notifyDataSetChanged()
    }
}

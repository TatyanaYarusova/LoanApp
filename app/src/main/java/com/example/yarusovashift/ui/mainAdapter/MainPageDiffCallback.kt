package com.example.yarusovashift.ui.mainAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.yarusovashift.domain.entity.Loan

object MainPageDiffCallback : DiffUtil.ItemCallback<Loan>() {
    override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem.id == newItem.id
    }
}
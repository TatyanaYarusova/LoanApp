package com.example.yarusovashift.ui.mainAdapter

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.ListAdapter
import com.example.yarusovashift.R
import com.example.yarusovashift.databinding.LoanItemBinding
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanState
import javax.inject.Inject

class MainPageAdapter @Inject constructor(
    private val app: Application
) :
    ListAdapter<Loan, MainPageViewHolder>(MainPageDiffCallback) {

    var onLoanClickListener: OnLoanClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageViewHolder {
        val binding = LoanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainPageViewHolder, position: Int) {
        val loan = getItem(position)
        with(holder.binding) {
            idLoan.text =
                String.format(app.resources.getString(R.string.id_loan), loan.id.toString())
            dateLoan.text = loan.date
            amountLoan.text = String.format(
                app.resources.getString(R.string.amount_loan_text),
                loan.amount.toString()
            )
            when (loan.state) {
                LoanState.APPROVED -> {
                    statusLoan.text = app.resources.getString(R.string.approved)
                    statusLoan.setTextColor(
                        getColor(
                            app.applicationContext,
                            R.color.text_color_approved_status_loan
                        )
                    )
                }

                LoanState.REGISTERED -> {
                    statusLoan.text = app.resources.getString(R.string.registered)
                    statusLoan.setTextColor(
                        getColor(
                            app.applicationContext,
                            R.color.text_color_registered_status_loan
                        )
                    )

                }

                LoanState.REJECTED -> {
                    statusLoan.text = app.resources.getString(R.string.reject)
                    statusLoan.setTextColor(
                        getColor(
                            app.applicationContext,
                            R.color.text_color_rejected_status_loan
                        )
                    )
                }
            }
        }

        holder.itemView.setOnClickListener {
            onLoanClickListener?.onLoanClick(loan)
        }
    }


    interface OnLoanClickListener {
        fun onLoanClick(loan: Loan)
    }
}
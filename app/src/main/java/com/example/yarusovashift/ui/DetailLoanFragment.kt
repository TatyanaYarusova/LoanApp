package com.example.yarusovashift.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.R
import com.example.yarusovashift.data.mapper.LoanParcelableModel
import com.example.yarusovashift.data.mapper.toEntity
import com.example.yarusovashift.data.mapper.toParcelableModel
import com.example.yarusovashift.databinding.FragmentDetailLoanBinding
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanState
import com.example.yarusovashift.presentation.DetailsLoanViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import javax.inject.Inject


class DetailLoanFragment : Fragment() {

    private var _binding: FragmentDetailLoanBinding? = null
    private val binding get() = _binding!!

    private var _loan: Loan? = null
    private val loan get() = _loan ?: throw RuntimeException("Loan is null!")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailsLoanViewModel::class.java]
    }

    private val component by lazy {
        (activity as MainActivity).component
            .fragmentComponent().create()
    }

    private fun parseArguments() {
        val args = requireArguments()
        if (args.containsKey(LOAN)) {
            _loan = (args.getParcelable(LOAN) as? LoanParcelableModel)?.toEntity()
        } else {
            throw RuntimeException("Loan argument is absent")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailLoanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initButtons()
    }

    private fun initButtons() {
        binding.backButton.setOnClickListener {
            viewModel.backScreen()
        }

        binding.homeButton.setOnClickListener {
            viewModel.openMainLoanScreen()
        }
    }

    private fun initView() {
        with(binding) {
            titleIdLoan.text = String.format(
                requireContext().resources.getString(R.string.title_details_id),
                loan.id
            )

            name.text = loan.firstName
            lastName.text = loan.lastName
            phone.text = loan.phoneNumber

            idLoan.text = String.format(
                requireContext().resources.getString(R.string.title_details_id),
                loan.id
            )
            dateLoan.text = loan.date
            period.text = loan.period.toString()
            percent.text =
                String.format(requireContext().resources.getString(R.string.percent), loan.percent)
            sum.text = String.format(
                requireContext().resources.getString(R.string.amount_draft),
                loan.amount
            )

            when (loan.state) {
                LoanState.APPROVED -> {
                    state.text = requireContext().resources.getString(R.string.approved)
                    state.setTextColor(
                        getColor(
                            requireContext().applicationContext,
                            R.color.text_color_approved_status_loan
                        )
                    )
                }

                LoanState.REGISTERED -> {
                    state.text = requireContext().resources.getString(R.string.registered)
                    state.setTextColor(
                        getColor(
                            requireContext().applicationContext,
                            R.color.text_color_registered_status_loan
                        )
                    )

                }

                LoanState.REJECTED -> {
                    state.text = requireContext().resources.getString(R.string.reject)
                    state.setTextColor(
                        getColor(
                            requireContext().applicationContext,
                            R.color.text_color_rejected_status_loan
                        )
                    )
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance(loan: Loan) = DetailLoanFragment().apply {
            arguments = Bundle().apply {
                putParcelable(LOAN, loan.toParcelableModel())
            }
        }

        private const val LOAN = "extra_loan"
    }
}
package com.example.yarusovashift.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.R
import com.example.yarusovashift.databinding.FragmentMainLoanBinding
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.presentation.MainLoanViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import com.example.yarusovashift.presentation.state.ScreenState
import com.example.yarusovashift.ui.mainAdapter.MainPageAdapter
import javax.inject.Inject

class MainLoanFragment : Fragment() {

    private var _binding: FragmentMainLoanBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainLoanViewModel::class.java]
    }

    @Inject
    lateinit var adapter: MainPageAdapter

    private var loans: List<Loan> = emptyList()
    private var amount: Long = 7000
    lateinit private var conditions: LoanConditions

    private val component by lazy {
        (activity as MainActivity).component
            .fragmentComponent().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        viewModel.getAllLoan()
        viewModel.getConditions()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainLoanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpRV()
        setUpSlider()
        setUpButtons()
        observeViewModel()
    }

    private fun initView() {
        binding.amountLoan.text = String.format(
            requireContext().resources.getString(R.string.amount_loan),
            amount
        )

        binding.allLoansCard.visibility = View.GONE
        binding.emptyLoansText.visibility = View.VISIBLE

    }

    private fun setUpRV() {
        adapter.onLoanClickListener = object : MainPageAdapter.OnLoanClickListener {
            override fun onLoanClick(loan: Loan) {
                viewModel.openDetailsLoanScreen(loan)
            }
        }

        binding.rvMainPage.adapter = adapter
    }

    private fun setUpButtons() {
        binding.onboardingButton.setOnClickListener {
            viewModel.openOnboardingScreen()
        }

        binding.addLoanButton.setOnClickListener {
            viewModel.openCreateLoanScreen(amount, conditions)
        }

        binding.editLoanAmountButton.setOnClickListener {

        }

        binding.showAllLoansButton.setOnClickListener {
            viewModel.openAllLoanScreen()
        }
        binding.menuButton.setOnClickListener {
            viewModel.openMenuScreen()
        }

    }

    private fun setUpSlider() {
        binding.priceSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                amount = (progress * 100).toLong()
                binding.amountLoan.text = String.format(
                    requireContext().resources.getString(R.string.amount_loan),
                    progress * 100
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                binding.amountLoan.text = String.format(
                    requireContext().resources.getString(R.string.amount_loan),
                    seekBar.progress * 100
                )
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                binding.amountLoan.text = String.format(
                    requireContext().resources.getString(R.string.amount_loan),
                    seekBar.progress * 100
                )
            }
        })
    }

    private fun observeViewModel() {
        viewModel.stateLoan.observe(viewLifecycleOwner, ::renderLoanState)

        viewModel.stateCondition.observe(viewLifecycleOwner, ::renderConditionState)

    }

    private fun renderConditionState(state: ScreenState<LoanConditions>) {
        when (state) {
            is ScreenState.Content -> {
                conditions = state.content
                renderCondition(state.content)
            }

            is ScreenState.Error -> renderConditionError()
            else -> {}
        }
    }

    private fun renderConditionError() {
        binding.conditionsDescriptionText.text =
            requireContext().resources.getString(R.string.error_conditions_description_text)
        binding.addLoanButton.isEnabled = false
    }

    private fun renderCondition(conditions: LoanConditions) {
        binding.conditionsDescriptionText.text = String.format(
            requireContext().resources.getString(R.string.conditions_description_text),
            conditions.percent.toString(),
            conditions.period.toString()
        )
    }

    private fun renderLoanState(state: ScreenState<List<Loan>>) {

        when (state) {
            is ScreenState.Loading -> renderLoanLoading()
            is ScreenState.Content -> renderContent(state.content)
            else -> {}
        }

    }

    private fun renderLoanLoading() {
        binding.pb.visibility = View.VISIBLE
        binding.layout.visibility = View.GONE
    }

    private fun renderContent(content: List<Loan>) {
        binding.pb.visibility = View.GONE
        binding.layout.visibility = View.VISIBLE
        if (content.isNotEmpty()) {
            loans = content
            val subLoanList = loans.subList(0, minOf(3, loans.size))
            adapter.submitList(subLoanList)
            binding.allLoansCard.visibility = View.VISIBLE
            binding.emptyLoansText.visibility = View.GONE
        } else {
            binding.allLoansCard.visibility = View.GONE
            binding.emptyLoansText.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllLoan()
        viewModel.getConditions()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainLoanFragment()

    }
}
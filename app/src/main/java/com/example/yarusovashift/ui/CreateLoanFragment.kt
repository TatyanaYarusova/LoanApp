package com.example.yarusovashift.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.R
import com.example.yarusovashift.data.mapper.ConditionLoanParcelableModel
import com.example.yarusovashift.data.mapper.toEntity
import com.example.yarusovashift.data.mapper.toParcelableModel
import com.example.yarusovashift.databinding.FragmentCreateLoanBinding
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.domain.entity.LoanState
import com.example.yarusovashift.presentation.CreateLoanViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import com.example.yarusovashift.presentation.state.ScreenState
import com.example.yarusovashift.utils.isValidFirstName
import com.example.yarusovashift.utils.isValidLastName
import com.example.yarusovashift.utils.isValidNumber
import javax.inject.Inject


class CreateLoanFragment : Fragment() {


    private var _binding: FragmentCreateLoanBinding? = null
    private val binding get() = _binding!!

    private var _amount: Long? = null
    private val amount get() = _amount ?: throw RuntimeException("Amount is null!")

    private var _conditions: LoanConditions? = null
    private val conditions get() = _conditions ?: throw RuntimeException("conditions is null!")


    private fun parseArguments() {
        val args = requireArguments()
        if (args.containsKey(AMOUNT_LOAN)) {
            _amount = args.getLong(AMOUNT_LOAN)
        } else {
            throw RuntimeException("Amount argument is absent")
        }

        if (args.containsKey(CONDITION_LOAN)) {
            _conditions =
                (args.getParcelable(CONDITION_LOAN) as? ConditionLoanParcelableModel)?.toEntity()
        } else {
            throw RuntimeException("Conditions argument is absent")
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CreateLoanViewModel::class.java]
    }

    private val component by lazy {
        (activity as MainActivity).component
            .fragmentComponent().create()
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
        _binding = FragmentCreateLoanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        binding.backButton.setOnClickListener {
            viewModel.openMainLoanScreen()
        }

        binding.createLoanButton.setOnClickListener {
            refreshInputField()
            val firstName = binding.firstNameTI.text.toString()
            val lastName = binding.lastNameTI.text.toString()
            val phoneNumber = binding.numberTI.text.toString()
            if (isValidData(firstName, lastName, phoneNumber)) {
                viewModel.createLoan(amount, firstName, lastName, phoneNumber, conditions)
            }
        }

        binding.menuButton.setOnClickListener {
            viewModel.openMenuScreen()
        }

    }

    private fun refreshInputField() {
        binding.errorFirstName.text = ""
        binding.errorLastName.text = ""
        binding.errorNumber.text = ""

    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
    }

    private fun renderState(state: ScreenState<Loan>) {

        when (state) {
            is ScreenState.Loading -> {}
            is ScreenState.Content -> renderContent(state.content.state)
            is ScreenState.Success -> viewModel.openSuccessScreen(amount)
            is ScreenState.Error -> viewModel.openErrorScreen(amount)

        }

    }

    private fun renderContent(content: LoanState) {
        when (content) {
            LoanState.APPROVED -> viewModel.openSuccessScreen(amount)
            LoanState.REGISTERED -> viewModel.openRegistrationScreen(amount)
            LoanState.REJECTED -> viewModel.openErrorScreen(amount)
        }
    }


    private fun isValidData(firstName: String, lastName: String, phoneNumber: String): Boolean {
        var flag = true
        if (!isValidFirstName(firstName)) {
            flag = false
            binding.errorFirstName.text = requireContext().resources.getString(R.string.error_alpha)
        }

        if (!isValidLastName(lastName)) {
            flag = false
            binding.errorLastName.text = requireContext().resources.getString(R.string.error_alpha)
        }

        if (!isValidNumber(phoneNumber)) {
            flag = false
            binding.errorNumber.text = requireContext().resources.getString(R.string.error_number)
        }

        if (firstName.isBlank()) {
            flag = false
            binding.errorFirstName.text =
                requireContext().resources.getString(R.string.error_empty_input)
        }

        if (lastName.isBlank()) {
            binding.errorLastName.text =
                requireContext().resources.getString(R.string.error_empty_input)
        }

        if (phoneNumber.isBlank()) {
            binding.errorNumber.text =
                requireContext().resources.getString(R.string.error_empty_input)
        }

        return flag
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance(amount: Long, conditions: LoanConditions) = CreateLoanFragment().apply {
            arguments = Bundle().apply {
                putLong(AMOUNT_LOAN, amount)
                putParcelable(CONDITION_LOAN, conditions.toParcelableModel())
            }
        }

        private const val AMOUNT_LOAN = "extra_amount_loan"
        private const val CONDITION_LOAN = "extra_condition_loan"
    }
}
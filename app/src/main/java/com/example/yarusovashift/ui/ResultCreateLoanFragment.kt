package com.example.yarusovashift.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.R
import com.example.yarusovashift.databinding.FragmentResultCreateLoanBinding
import com.example.yarusovashift.presentation.ResultViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import javax.inject.Inject


class ResultCreateLoanFragment : Fragment() {

    private var _flag: Int? = null
    private val flag get() = _flag ?: throw RuntimeException("Flag is null!")

    private var _amount: Long? = null
    private val amount get() = _amount ?: throw RuntimeException("Amount is null!")

    private var _binding: FragmentResultCreateLoanBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ResultViewModel::class.java]
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

    private fun parseArguments() {
        val args = requireArguments()
        if (args.containsKey(RESULT)) {
            _flag = args.getInt(RESULT)
        } else {
            throw RuntimeException("flag result argument is absent")
        }

        if (args.containsKey(AMOUNT)) {
            _amount = args.getLong(AMOUNT)
        } else {
            throw RuntimeException("amount argument is absent")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultCreateLoanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.closeButton.setOnClickListener {
            viewModel.openMainLoanScreen()
        }

        if (flag == SUCCESS) {
            binding.img.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.success_img
                )
            )
            binding.titleResult.text = String.format(
                requireContext().resources.getString(R.string.success_result_text_title),
                amount.toString()
            )
            binding.descriptionResult.text =
                requireContext().resources.getString(R.string.success_description_result)
            binding.buttonResult.text =
                requireContext().resources.getString(R.string.success_text_button)
            binding.buttonResult.setOnClickListener {
                viewModel.openBankScreen()
            }
        }

        if (flag == ERROR) {
            binding.img.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.error_img
                )
            )
            binding.titleResult.text =
                requireContext().resources.getString(R.string.error_result_text_title)
            binding.descriptionResult.text =
                requireContext().resources.getString(R.string.error_description_result)
            binding.buttonResult.text =
                requireContext().resources.getString(R.string.error_text_button)
            binding.buttonResult.setOnClickListener {
                viewModel.openMainLoanScreen()
            }
        }

        if (flag == REG) {
            binding.img.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.success_img
                )
            )
            binding.titleResult.text =
                requireContext().resources.getString(R.string.registr_result_text_title)
            binding.descriptionResult.text =
                requireContext().resources.getString(R.string.registr_description_result)
            binding.buttonResult.text =
                requireContext().resources.getString(R.string.error_text_button)
            binding.buttonResult.setOnClickListener {
                viewModel.openMainLoanScreen()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(flag: Int, amount: Long) = ResultCreateLoanFragment().apply {
            arguments = Bundle().apply {
                putInt(RESULT, flag)
                putLong(AMOUNT, amount)
            }
        }

        private const val RESULT = "extra_result"
        private const val AMOUNT = "extra_amount"
        private const val REG = 2
        private const val SUCCESS = 1
        private const val ERROR = 0
    }
}
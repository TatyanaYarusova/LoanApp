package com.example.yarusovashift.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.R
import com.example.yarusovashift.databinding.FragmentOnboardingBinding
import com.example.yarusovashift.presentation.OnboardingViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import com.example.yarusovashift.presentation.state.OnboardingState
import javax.inject.Inject


class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[OnboardingViewModel::class.java]
    }

    private val component by lazy {
        (activity as MainActivity).component
            .fragmentComponent().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initButton()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
    }

    private fun renderState(state: OnboardingState) {
        when (state) {
            is OnboardingState.First -> renderFirstInfo()
            is OnboardingState.Second -> renderSecondInfo()
            is OnboardingState.Third -> renderThirdInfo()
        }
    }

    private fun initButton() {
        binding.backButton.setOnClickListener {
            viewModel.backInfo()
        }
        binding.nextButton.setOnClickListener {
            viewModel.nextInfo()
        }

        binding.closeButton.setOnClickListener {
            viewModel.closeOnboarding()
        }
    }

    private fun renderFirstInfo() {
        binding.backButton.visibility = View.INVISIBLE
        binding.titleText.text = getString(R.string.get_loan)
        binding.descriptionFirst.text = getString(R.string.create_loan_description_first)
        binding.descriptionSecond.text = getString(R.string.create_loan_description_second)
        binding.img.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.illuctration_first
            )
        )
        binding.navDot.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.dot_first
            )
        )
    }

    private fun renderSecondInfo() {
        binding.backButton.visibility = View.VISIBLE
        binding.descriptionSecond.visibility = View.VISIBLE
        binding.titleText.text = getString(R.string.create_loan)
        binding.descriptionFirst.text = getString(R.string.get_loan_description_first)
        binding.descriptionSecond.text = getString(R.string.get_loan_description_second)
        binding.img.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.illuctration_second
            )
        )
        binding.navDot.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.dot_second
            )
        )
        binding.nextButton.text = getString(R.string.next_button_text)
    }

    private fun renderThirdInfo() {
        binding.titleText.text = getString(R.string.all_loans)
        binding.descriptionFirst.text = getString(R.string.all_loans_description_first)
        binding.descriptionSecond.visibility = View.GONE
        binding.img.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.illuctration_third
            )
        )
        binding.navDot.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.dot_third
            )
        )
        binding.nextButton.text = getString(R.string.close_button_text)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = OnboardingFragment()

    }
}
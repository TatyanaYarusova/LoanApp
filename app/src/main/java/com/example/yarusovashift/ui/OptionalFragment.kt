package com.example.yarusovashift.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.R
import com.example.yarusovashift.databinding.FragmentOptionalBinding
import com.example.yarusovashift.presentation.OptionalViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import javax.inject.Inject

class OptionalFragment : Fragment() {

    private var _type: Int? = null
    private val type get() = _type ?: throw RuntimeException("Type is null!")


    private var _binding: FragmentOptionalBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[OptionalViewModel::class.java]
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
        if (args.containsKey(TYPE)) {
            _type = args.getInt(TYPE)
        } else {
            throw RuntimeException("type argument is absent")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptionalBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        when (type) {
            BANK -> renderBankScreen()
            OFFERS -> renderOffersScreen()
            HELP -> renderHelpScreen()
        }
    }

    private fun renderBankScreen() {
        binding.buttonResult.setOnClickListener {
            viewModel.openMainLoanScreen()
        }

        binding.closeButton.setOnClickListener {
            viewModel.closeCurrentScreen()
        }

        binding.img.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.sorry))

        binding.buttonResult.text = requireContext().resources.getString(R.string.text_button_bank)
        binding.titleResult.text = requireContext().resources.getString(R.string.text_title_bank)

    }

    private fun renderOffersScreen() {
        binding.buttonResult.setOnClickListener {
            viewModel.openBankScreen()
        }

        binding.closeButton.setOnClickListener {
            viewModel.closeCurrentScreen()
        }

        binding.img.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.offers_img
            )
        )

        binding.buttonResult.text =
            requireContext().resources.getString(R.string.text_button_offers)
        binding.titleResult.text = requireContext().resources.getString(R.string.offers_title_text)
        binding.descriptionResult.text =
            requireContext().resources.getString(R.string.text_description_offers)
    }

    private fun renderHelpScreen() {
        binding.buttonResult.visibility = View.INVISIBLE

        binding.closeButton.setOnClickListener {
            viewModel.closeCurrentScreen()
        }

        binding.img.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.help_img
            )
        )

        binding.titleResult.text = requireContext().resources.getString(R.string.help_title_text)
        binding.descriptionResult.text =
            requireContext().resources.getString(R.string.text_description_help)
    }

    companion object {

        fun newInstance(type: Int) = OptionalFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE, type)
            }
        }

        private const val TYPE = "extra_type"

        private const val BANK = 0
        private const val HELP = 1
        private const val OFFERS = 2
    }
}
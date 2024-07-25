package com.example.yarusovashift.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.R
import com.example.yarusovashift.databinding.FragmentMenuBinding
import com.example.yarusovashift.presentation.MenuViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import javax.inject.Inject

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
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
        _binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() {
        binding.homeButton.setOnClickListener {
            viewModel.openMainLoanScreen()
        }

        binding.onboardingButton.setOnClickListener {
            viewModel.openOnboardingScreen()
        }

        binding.allLoansButton.setOnClickListener {
            viewModel.openAllLoanScreen()
        }

        binding.offersButton.setOnClickListener {
            viewModel.openOffersScreen()
        }

        binding.banksButton.setOnClickListener {
            viewModel.openBankScreen()
        }

        binding.helpButton.setOnClickListener {
            viewModel.openHelpScreen()
        }

        binding.languageButton.setOnClickListener {
            viewModel.openLanguageScreen()
        }

        binding.logoutButton.setOnClickListener {
            logOutDialog()
        }
    }

    private fun logOutDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.logout_dialog)

        val logoutButton = dialog.findViewById<Button>(R.id.logout_button)
        val cancelButton = dialog.findViewById<Button>(R.id.cancel_button)

        logoutButton.setOnClickListener {
            viewModel.logout()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance() = MenuFragment()

    }
}
package com.example.yarusovashift.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.databinding.FragmentAllLoansBinding
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.presentation.AllLoanViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import com.example.yarusovashift.presentation.state.ScreenState
import com.example.yarusovashift.ui.mainAdapter.MainPageAdapter
import javax.inject.Inject


class AllLoansFragment : Fragment() {

    private var _binding: FragmentAllLoansBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AllLoanViewModel::class.java]
    }

    @Inject
    lateinit var adapter: MainPageAdapter

    private val component by lazy {
        (activity as MainActivity).component
            .fragmentComponent().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        viewModel.getAllLoan()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllLoansBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.stateLoan.observe(viewLifecycleOwner, ::renderLoanState)
    }

    private fun renderLoanState(state: ScreenState<List<Loan>>) {

        when (state) {
            is ScreenState.Loading -> renderLoading()
            is ScreenState.Content -> renderContent(state.content)
            else -> {}
        }

    }

    private fun renderLoading() {
        binding.pb.visibility = View.VISIBLE
        binding.rvAllLoanPage.visibility = View.GONE
    }

    private fun renderContent(loan: List<Loan>) {
        binding.pb.visibility = View.GONE
        binding.rvAllLoanPage.visibility = View.VISIBLE

        binding.backButton.setOnClickListener {
            viewModel.openMainLoanScreen()
        }

        binding.homeButton.setOnClickListener {
            viewModel.openMainLoanScreen()
        }

        adapter.onLoanClickListener = object : MainPageAdapter.OnLoanClickListener {
            override fun onLoanClick(loan: Loan) {
                viewModel.openDetailsLoanScreen(loan)
            }
        }
        adapter.submitList(loan)
        binding.rvAllLoanPage.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = AllLoansFragment()
    }
}
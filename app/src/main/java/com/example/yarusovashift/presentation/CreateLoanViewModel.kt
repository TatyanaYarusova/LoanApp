package com.example.yarusovashift.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yarusovashift.data.model.LoanBody
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.domain.entity.result.RequestResult
import com.example.yarusovashift.domain.usecase.loan.CreateLoanUseCase
import com.example.yarusovashift.navigation.repository.CreateLoanScreenRouter
import com.example.yarusovashift.presentation.state.ScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateLoanViewModel @Inject constructor(
    private val createLoanUseCase: CreateLoanUseCase,
    private val router: CreateLoanScreenRouter
) : ViewModel() {

    private val _state: MutableLiveData<ScreenState<Loan>> =
        MutableLiveData(ScreenState.Loading)
    val state: LiveData<ScreenState<Loan>> = _state

    fun createLoan(
        amount: Long,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        conditions: LoanConditions
    ) {
        viewModelScope.launch {
            _state.value = ScreenState.Loading
            val loanBody = LoanBody(
                amount = amount,
                firstName = firstName,
                lastName = lastName,
                percent = conditions.percent,
                period = conditions.period,
                phoneNumber = phoneNumber
            )

            try {
                val loan = createLoanUseCase(loanBody)
                when (loan) {
                    is RequestResult.Success -> {
                        _state.value = ScreenState.Content(loan.content)
                    }

                    is RequestResult.Error -> {
                        _state.value = ScreenState.Error
                    }
                }
            } catch (e: Exception) {
                _state.value = ScreenState.Error
            }
        }

    }


    fun openSuccessScreen(amount: Long) {
        router.openSuccessScreen(amount)
    }

    fun openRegistrationScreen(amount: Long) {
        router.openRegScreen(amount)
    }

    fun openErrorScreen(amount: Long) {
        router.openErrorScreen(amount)
    }

    fun openMainLoanScreen() {
        router.openMainLoanScreen()
    }

    fun openMenuScreen() {
        router.openMenuScreen()
    }

}
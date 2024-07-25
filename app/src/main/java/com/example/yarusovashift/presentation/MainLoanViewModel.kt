package com.example.yarusovashift.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.domain.entity.result.RequestError
import com.example.yarusovashift.domain.entity.result.RequestResult
import com.example.yarusovashift.domain.usecase.loan.GetLoanConditionsUseCase
import com.example.yarusovashift.domain.usecase.loan.GetLoanListUseCase
import com.example.yarusovashift.navigation.repository.MainLoanScreenRouter
import com.example.yarusovashift.presentation.state.ScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainLoanViewModel @Inject constructor(
    private val getLoanListUseCase: GetLoanListUseCase,
    private val getLoanConditionUseCase: GetLoanConditionsUseCase,
    private val router: MainLoanScreenRouter
) : ViewModel() {

    private val _stateLoan: MutableLiveData<ScreenState<List<Loan>>> =
        MutableLiveData(ScreenState.Loading)
    val stateLoan: LiveData<ScreenState<List<Loan>>> = _stateLoan

    private val _stateCondition: MutableLiveData<ScreenState<LoanConditions>> =
        MutableLiveData(ScreenState.Loading)
    val stateCondition: LiveData<ScreenState<LoanConditions>> = _stateCondition

    fun getAllLoan() {
        viewModelScope.launch {
            try {
                val loans = getLoanListUseCase()
                when (loans) {
                    is RequestResult.Success -> _stateLoan.value =
                        ScreenState.Content(loans.content)

                    is RequestResult.Error -> handleError(loans.requestError)
                }
            } catch (e: Exception) {
                _stateLoan.value = ScreenState.Content(emptyList())
            }
        }
    }

    fun getConditions() {
        viewModelScope.launch {
            try {
                val conditions = getLoanConditionUseCase()
                when (conditions) {
                    is RequestResult.Success -> _stateCondition.value =
                        ScreenState.Content(conditions.content)

                    is RequestResult.Error -> _stateCondition.value = ScreenState.Error
                }
            } catch (e: Exception) {
                _stateCondition.value = ScreenState.Error
            }
        }
    }

    private fun handleError(errorType: RequestError<List<Loan>>) {
        when (errorType) {
            is RequestError.NetworkError -> _stateLoan.value =
                ScreenState.Content(errorType.cache ?: emptyList())

            is RequestError.ServerError -> ScreenState.Content(errorType.cache ?: emptyList())
            else -> ScreenState.Error
        }
    }

    fun openMenuScreen() {
        router.openMenuScreen()
    }

    fun openAllLoanScreen() {
        router.openAllLoanScreen()
    }

    fun openCreateLoanScreen(amount: Long, conditions: LoanConditions) {
        router.openCreateLoanScreen(amount, conditions)
    }

    fun openDetailsLoanScreen(loan: Loan) {
        router.openDetailsLoanScreen(loan)
    }

    fun openOnboardingScreen() {
        router.openOnboardingScreen()
    }
}
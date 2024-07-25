package com.example.yarusovashift.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.result.RequestError
import com.example.yarusovashift.domain.entity.result.RequestResult
import com.example.yarusovashift.domain.usecase.loan.GetLoanListUseCase
import com.example.yarusovashift.navigation.repository.AllLoanScreenRouter
import com.example.yarusovashift.presentation.state.ScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllLoanViewModel @Inject constructor(
    private val getLoanListUseCase: GetLoanListUseCase,
    private val router: AllLoanScreenRouter
) : ViewModel() {


    private val _stateLoan: MutableLiveData<ScreenState<List<Loan>>> =
        MutableLiveData(ScreenState.Loading)
    val stateLoan: LiveData<ScreenState<List<Loan>>> = _stateLoan

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

    private fun handleError(errorType: RequestError<List<Loan>>) {
        when (errorType) {
            is RequestError.NetworkError -> _stateLoan.value =
                ScreenState.Content(errorType.cache ?: emptyList())

            is RequestError.ServerError -> ScreenState.Content(errorType.cache ?: emptyList())
            else -> ScreenState.Error
        }
    }

    fun openDetailsLoanScreen(loan: Loan) {
        router.openDetailsLoanScreen(loan)
    }

    fun openMainLoanScreen() {
        router.openMainLoanScreen()
    }

}
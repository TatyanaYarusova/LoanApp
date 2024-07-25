package com.example.yarusovashift.presentation

import androidx.lifecycle.ViewModel
import com.example.yarusovashift.navigation.repository.ResultCreateLoanScreenRouter
import javax.inject.Inject

class ResultViewModel @Inject constructor(
    private val router: ResultCreateLoanScreenRouter
) : ViewModel() {


    fun openMainLoanScreen() {
        router.openMainLoanScreen()
    }

    fun openBankScreen() {
        router.openBankScreen()
    }
}
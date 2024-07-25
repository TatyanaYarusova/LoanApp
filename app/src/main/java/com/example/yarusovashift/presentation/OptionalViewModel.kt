package com.example.yarusovashift.presentation

import androidx.lifecycle.ViewModel
import com.example.yarusovashift.navigation.repository.OptionalScreenRouter
import javax.inject.Inject

class OptionalViewModel @Inject constructor(
    private val router: OptionalScreenRouter
) : ViewModel() {

    fun closeCurrentScreen() {
        router.closeCurrentScreen()
    }

    fun openBankScreen() {
        router.openBankScreen()
    }

    fun openMainLoanScreen() {
        router.openMainLoanScreen()
    }

}
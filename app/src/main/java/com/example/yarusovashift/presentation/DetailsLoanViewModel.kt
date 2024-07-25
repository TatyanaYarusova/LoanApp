package com.example.yarusovashift.presentation

import androidx.lifecycle.ViewModel
import com.example.yarusovashift.navigation.repository.DetailsLoanScreenRouter
import javax.inject.Inject

class DetailsLoanViewModel @Inject constructor(
    private val router: DetailsLoanScreenRouter
) : ViewModel() {

    fun openMainLoanScreen() {
        router.openMainLoanScreen()
    }

    fun backScreen() {
        router.backScreen()
    }
}
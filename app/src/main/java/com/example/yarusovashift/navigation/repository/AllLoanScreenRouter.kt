package com.example.yarusovashift.navigation.repository

import com.example.yarusovashift.domain.entity.Loan

interface AllLoanScreenRouter {
    fun openDetailsLoanScreen(loan: Loan)

    fun openMainLoanScreen()
}
package com.example.yarusovashift.navigation.repository

import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions

interface MainLoanScreenRouter {

    fun openOnboardingScreen()

    fun openMenuScreen()

    fun openAllLoanScreen()

    fun openCreateLoanScreen(amount: Long, conditions: LoanConditions)

    fun openDetailsLoanScreen(loan: Loan)
}
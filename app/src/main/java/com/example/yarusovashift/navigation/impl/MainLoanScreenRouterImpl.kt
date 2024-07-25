package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.navigation.repository.MainLoanScreenRouter
import com.example.yarusovashift.navigation.screens.getAllLoanScreen
import com.example.yarusovashift.navigation.screens.getCreateLoanScreen
import com.example.yarusovashift.navigation.screens.getDetailsLoanScreen
import com.example.yarusovashift.navigation.screens.getMenuScreen
import com.example.yarusovashift.navigation.screens.getOnboardingScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainLoanScreenRouterImpl @Inject constructor(
    private val router: Router
) : MainLoanScreenRouter {

    override fun openOnboardingScreen() {
        router.navigateTo(getOnboardingScreen())
    }

    override fun openMenuScreen() {
        router.navigateTo(getMenuScreen())
    }

    override fun openAllLoanScreen() {
        router.navigateTo(getAllLoanScreen())
    }

    override fun openCreateLoanScreen(amount: Long, conditions: LoanConditions) {
        router.navigateTo(getCreateLoanScreen(amount, conditions))
    }

    override fun openDetailsLoanScreen(loan: Loan) {
        router.navigateTo(getDetailsLoanScreen(loan))
    }

}
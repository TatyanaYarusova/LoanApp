package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.navigation.repository.AllLoanScreenRouter
import com.example.yarusovashift.navigation.screens.getDetailsLoanScreen
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class AllLoanScreenRouterImpl @Inject constructor(
    private val router: Router
) : AllLoanScreenRouter {

    override fun openDetailsLoanScreen(loan: Loan) {
        router.navigateTo(getDetailsLoanScreen(loan))
    }

    override fun openMainLoanScreen() {
        router.exit()
    }
}
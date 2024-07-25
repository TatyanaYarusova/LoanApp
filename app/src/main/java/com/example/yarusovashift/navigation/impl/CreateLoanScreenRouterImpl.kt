package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.CreateLoanScreenRouter
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.example.yarusovashift.navigation.screens.getMenuScreen
import com.example.yarusovashift.navigation.screens.getResultScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CreateLoanScreenRouterImpl @Inject constructor(
    private val router: Router
) : CreateLoanScreenRouter {
    override fun openErrorScreen(amount: Long) {
        router.navigateTo(getResultScreen(ERROR, amount))
    }

    override fun openSuccessScreen(amount: Long) {
        router.navigateTo(getResultScreen(SUCCESS, amount))
    }

    override fun openMainLoanScreen() {
        router.navigateTo(getMainLoanScreen())
    }

    override fun openMenuScreen() {
        router.navigateTo(getMenuScreen())
    }

    override fun openRegScreen(amount: Long) {
        router.navigateTo(getResultScreen(REG, amount))
    }

    companion object {
        private const val REG = 2
        private const val SUCCESS = 1
        private const val ERROR = 0
    }
}
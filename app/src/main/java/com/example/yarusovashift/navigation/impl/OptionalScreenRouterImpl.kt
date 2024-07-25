package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.OptionalScreenRouter
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.example.yarusovashift.navigation.screens.getOptionalScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class OptionalScreenRouterImpl @Inject constructor(
    private val router: Router
) : OptionalScreenRouter {

    override fun closeCurrentScreen() {
        router.exit()
    }

    override fun openBankScreen() {
        router.navigateTo(getOptionalScreen(BANK))
    }

    override fun openMainLoanScreen() {
        router.backTo(getMainLoanScreen())
    }

    companion object {
        private const val BANK = 0
    }
}
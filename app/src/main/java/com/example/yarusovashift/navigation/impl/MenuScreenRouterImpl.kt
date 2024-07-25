package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.MenuScreenRouter
import com.example.yarusovashift.navigation.screens.getAllLoanScreen
import com.example.yarusovashift.navigation.screens.getAuthScreen
import com.example.yarusovashift.navigation.screens.getLanguageScreen
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.example.yarusovashift.navigation.screens.getMenuScreen
import com.example.yarusovashift.navigation.screens.getOnboardingScreen
import com.example.yarusovashift.navigation.screens.getOptionalScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MenuScreenRouterImpl @Inject constructor(
    private val router: Router
) : MenuScreenRouter {
    override fun openMenuScreen() {
        router.navigateTo(getMenuScreen())
    }

    override fun openAuthScreen() {
        router.newRootScreen(getAuthScreen())
    }

    override fun openHelpScreen() {
        router.navigateTo(getOptionalScreen(HELP))
    }

    override fun openLanguageScreen() {
        router.navigateTo(getLanguageScreen())
    }

    override fun openMainLoanScreen() {
        router.newRootScreen(getMainLoanScreen())
    }

    override fun openOffersScreen() {
        router.navigateTo(getOptionalScreen(OFFERS))
    }

    override fun openOnboardingScreen() {
        router.navigateTo(getOnboardingScreen())
    }

    override fun openAllLoanScreen() {
        router.navigateTo(getAllLoanScreen())
    }

    override fun openBankScreen() {
        router.navigateTo(getOptionalScreen(BANK))
    }

    companion object {
        private const val BANK = 0
        private const val HELP = 1
        private const val OFFERS = 2
    }
}
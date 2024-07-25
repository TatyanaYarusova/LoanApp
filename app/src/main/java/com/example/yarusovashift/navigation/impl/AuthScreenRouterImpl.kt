package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.AuthScreenRouter
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.example.yarusovashift.navigation.screens.getOnboardingScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class AuthScreenRouterImpl @Inject constructor(
    private val router: Router
) : AuthScreenRouter {
    override fun openMainLoanScreen() {
        router.newRootScreen(getMainLoanScreen())
    }

    override fun openOnboardingScreen() {
        router.newRootScreen(getOnboardingScreen())
    }
}
package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.OnboardingScreenRouter
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class OnboardingScreenRouterImpl @Inject constructor(
    private val router: Router
) : OnboardingScreenRouter {
    override fun openMainScreen() {
        router.newRootScreen(getMainLoanScreen())
    }
}
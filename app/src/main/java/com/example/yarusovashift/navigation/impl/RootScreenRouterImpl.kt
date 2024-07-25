package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.RootScreenRouter
import com.example.yarusovashift.navigation.screens.getAuthScreen
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class RootScreenRouterImpl @Inject constructor(
    private val router: Router
) : RootScreenRouter {
    override fun openAuthScreen() {
        router.newRootScreen(getAuthScreen())
    }

    override fun openMainLoanScreen() {
        router.newRootScreen(getMainLoanScreen())
    }
}
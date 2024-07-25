package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.DetailsLoanScreenRouter
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DetailsLoanScreenRouterImpl @Inject constructor(
    private val router: Router
) : DetailsLoanScreenRouter {


    override fun openMainLoanScreen() {
        router.newRootScreen(getMainLoanScreen())
    }

    override fun backScreen() {
        router.exit()
    }

}
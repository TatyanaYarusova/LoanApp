package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.ResultCreateLoanScreenRouter
import com.example.yarusovashift.navigation.screens.getMainLoanScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class ResultCreateLoanScreenRouterImpl @Inject constructor(
    private val router: Router
) : ResultCreateLoanScreenRouter {

    override fun openMainLoanScreen() {
        router.backTo(getMainLoanScreen())
    }

    override fun openBankScreen() {
        TODO("Not yet implemented")
    }
}
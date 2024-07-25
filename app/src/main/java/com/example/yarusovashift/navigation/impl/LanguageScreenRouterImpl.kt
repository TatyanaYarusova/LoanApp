package com.example.yarusovashift.navigation.impl

import com.example.yarusovashift.navigation.repository.LanguageScreenRouter
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LanguageScreenRouterImpl @Inject constructor(
    private val router: Router
) : LanguageScreenRouter {

    override fun openMenuScreen() {
        router.exit()
    }
}
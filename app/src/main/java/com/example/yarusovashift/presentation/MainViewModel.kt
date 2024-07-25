package com.example.yarusovashift.presentation

import androidx.lifecycle.ViewModel
import com.example.yarusovashift.domain.usecase.auth.CheckLoginUseCase
import com.example.yarusovashift.navigation.repository.RootScreenRouter
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val navigatorHolder: NavigatorHolder,
    private val checkLoginUseCase: CheckLoginUseCase,
    private val router: RootScreenRouter
) : ViewModel() {

    init {
        openStartScreen()
    }

    private fun openStartScreen() {
        if (checkLoginUseCase()) {
            router.openMainLoanScreen()
        } else {
            router.openAuthScreen()
        }
    }

    fun setNavigator(navigator: Navigator) {
        navigatorHolder.setNavigator(navigator)
    }

    fun removeNavigator() {
        navigatorHolder.removeNavigator()
    }


}
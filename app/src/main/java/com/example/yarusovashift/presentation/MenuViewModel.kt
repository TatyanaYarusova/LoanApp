package com.example.yarusovashift.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yarusovashift.domain.usecase.auth.LogoutUseCase
import com.example.yarusovashift.navigation.repository.MenuScreenRouter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val router: MenuScreenRouter,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun openAllLoanScreen() {
        router.openAllLoanScreen()
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
        router.openAuthScreen()
    }

    fun openMainLoanScreen() {
        router.openMainLoanScreen()
    }

    fun openOnboardingScreen() {
        router.openOnboardingScreen()
    }

    fun openOffersScreen() {
        router.openOffersScreen()
    }

    fun openBankScreen() {
        router.openBankScreen()
    }

    fun openHelpScreen() {
        router.openHelpScreen()
    }

    fun openLanguageScreen() {
        router.openLanguageScreen()
    }


}
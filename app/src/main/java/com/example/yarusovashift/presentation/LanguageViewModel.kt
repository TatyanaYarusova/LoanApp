package com.example.yarusovashift.presentation

import androidx.lifecycle.ViewModel
import com.example.yarusovashift.navigation.repository.LanguageScreenRouter
import javax.inject.Inject

class LanguageViewModel @Inject constructor(
    private val router: LanguageScreenRouter
) : ViewModel() {

    fun openMenuScreen() {
        router.openMenuScreen()
    }
}
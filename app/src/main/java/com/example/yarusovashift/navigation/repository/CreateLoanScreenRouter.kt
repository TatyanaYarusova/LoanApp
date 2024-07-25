package com.example.yarusovashift.navigation.repository

interface CreateLoanScreenRouter {

    fun openErrorScreen(amount: Long)

    fun openSuccessScreen(amount: Long)

    fun openRegScreen(amount: Long)

    fun openMainLoanScreen()

    fun openMenuScreen()
}
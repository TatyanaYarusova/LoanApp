package com.example.yarusovashift.presentation.state

sealed class SuccessState{
    object SuccessLogin : SuccessState()

    object SuccessRegistration : SuccessState()
}

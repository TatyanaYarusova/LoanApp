package com.example.yarusovashift.presentation.state

import com.example.yarusovashift.domain.entity.result.RequestError

sealed class AuthState {

    object Loading : AuthState()

    object Login : AuthState()

    object Registration : AuthState()

    data class Success(val type: SuccessState) : AuthState()

    data class Error<T>(val type: RequestError<T>)  : AuthState()
}
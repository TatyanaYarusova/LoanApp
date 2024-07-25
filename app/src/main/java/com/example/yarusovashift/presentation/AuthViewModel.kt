package com.example.yarusovashift.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yarusovashift.domain.entity.User
import com.example.yarusovashift.domain.entity.result.RequestResult
import com.example.yarusovashift.domain.usecase.auth.LoginUseCase
import com.example.yarusovashift.domain.usecase.auth.RegistrationUseCase
import com.example.yarusovashift.navigation.repository.AuthScreenRouter
import com.example.yarusovashift.presentation.state.AuthState
import com.example.yarusovashift.presentation.state.SuccessState
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val router: AuthScreenRouter
) : ViewModel() {

    companion object {
        val REGISTRATION_FLAG = 1
        val LOGIN_FLAG = 0
    }


    private val _state: MutableLiveData<AuthState> = MutableLiveData(AuthState.Login)
    val state: LiveData<AuthState> = _state

    fun login(name: String, password: String) {

        viewModelScope.launch {
            _state.value = AuthState.Loading
            val loginBody = User(name, password)
            val response = loginUseCase(loginBody)
            handleResponse(response, LOGIN_FLAG)

        }


    }

    fun registration(name: String, password: String, repeatPassword: String) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            val loginBody = User(name, password)
            val response = registrationUseCase(loginBody)
            handleResponse(response, REGISTRATION_FLAG)

        }
    }

    fun openRegistration() {
        _state.value = AuthState.Registration
    }

    fun openLogin() {
        _state.value = AuthState.Login
    }

    fun openOnboarding() {
        router.openOnboardingScreen()
    }

    fun openMain() {
        router.openMainLoanScreen()
    }


    private fun handleResponse(response: RequestResult<Unit>, flag: Int) {
        when (response) {
            is RequestResult.Success -> {
                if (flag == REGISTRATION_FLAG)
                    _state.value = AuthState.Success(SuccessState.SuccessRegistration)
                else
                    _state.value = AuthState.Success(SuccessState.SuccessLogin)
            }

            is RequestResult.Error -> {
                _state.value = AuthState.Error(response.requestError)
            }
        }
    }
}
package com.example.yarusovashift.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarusovashift.navigation.repository.OnboardingScreenRouter
import com.example.yarusovashift.presentation.state.OnboardingState
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val router: OnboardingScreenRouter
) : ViewModel() {

    private val _state: MutableLiveData<OnboardingState> = MutableLiveData(OnboardingState.First)
    val state: LiveData<OnboardingState> = _state

    fun nextInfo() {
        when (state.value) {
            is OnboardingState.First -> _state.value = OnboardingState.Second
            is OnboardingState.Second -> _state.value = OnboardingState.Third
            is OnboardingState.Third -> router.openMainScreen()
            else -> {}
        }
    }

    fun backInfo() {
        when (state.value) {
            is OnboardingState.Second -> _state.value = OnboardingState.First
            is OnboardingState.Third -> _state.value = OnboardingState.Second
            else -> {}
        }
    }

    fun closeOnboarding() {
        router.openMainScreen()
    }
}
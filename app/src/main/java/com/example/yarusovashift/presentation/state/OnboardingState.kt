package com.example.yarusovashift.presentation.state

import java.util.Objects

sealed class OnboardingState {
    object First : OnboardingState()

    object Second : OnboardingState()

    object Third: OnboardingState()
}


package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.ui.OnboardingFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getOnboardingScreen() = FragmentScreen { OnboardingFragment.newInstance() }
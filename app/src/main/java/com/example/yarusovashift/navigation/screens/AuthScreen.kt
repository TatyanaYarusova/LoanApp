package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.ui.AuthFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getAuthScreen() = FragmentScreen { AuthFragment.newInstance() }
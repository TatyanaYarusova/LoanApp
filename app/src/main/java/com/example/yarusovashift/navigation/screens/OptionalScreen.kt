package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.ui.OptionalFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getOptionalScreen(type: Int) = FragmentScreen { OptionalFragment.newInstance(type) }
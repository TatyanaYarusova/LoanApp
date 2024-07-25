package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.ui.LanguageFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getLanguageScreen() = FragmentScreen { LanguageFragment.newInstance() }
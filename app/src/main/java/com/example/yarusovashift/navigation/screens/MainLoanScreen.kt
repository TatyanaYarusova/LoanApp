package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.ui.MainLoanFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getMainLoanScreen() = FragmentScreen { MainLoanFragment.newInstance() }
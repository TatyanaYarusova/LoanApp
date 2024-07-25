package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.ui.ResultCreateLoanFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getResultScreen(flag: Int, amount: Long) = FragmentScreen { ResultCreateLoanFragment.newInstance(flag, amount) }
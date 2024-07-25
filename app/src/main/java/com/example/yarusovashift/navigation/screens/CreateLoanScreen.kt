package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.ui.CreateLoanFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getCreateLoanScreen(amount: Long, conditions: LoanConditions) = FragmentScreen { CreateLoanFragment.newInstance(amount, conditions) }
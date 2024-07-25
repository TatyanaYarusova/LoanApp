package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.ui.DetailLoanFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getDetailsLoanScreen(loan: Loan) = FragmentScreen { DetailLoanFragment.newInstance(loan) }
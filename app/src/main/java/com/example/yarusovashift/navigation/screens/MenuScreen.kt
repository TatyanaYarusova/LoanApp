package com.example.yarusovashift.navigation.screens

import com.example.yarusovashift.ui.MenuFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getMenuScreen() = FragmentScreen { MenuFragment.newInstance() }
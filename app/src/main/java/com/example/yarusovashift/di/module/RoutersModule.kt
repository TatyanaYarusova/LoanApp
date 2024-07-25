package com.example.yarusovashift.di.module

import com.example.yarusovashift.navigation.impl.AllLoanScreenRouterImpl
import com.example.yarusovashift.navigation.impl.AuthScreenRouterImpl
import com.example.yarusovashift.navigation.impl.CreateLoanScreenRouterImpl
import com.example.yarusovashift.navigation.impl.DetailsLoanScreenRouterImpl
import com.example.yarusovashift.navigation.impl.LanguageScreenRouterImpl
import com.example.yarusovashift.navigation.impl.MainLoanScreenRouterImpl
import com.example.yarusovashift.navigation.impl.MenuScreenRouterImpl
import com.example.yarusovashift.navigation.impl.OnboardingScreenRouterImpl
import com.example.yarusovashift.navigation.impl.OptionalScreenRouterImpl
import com.example.yarusovashift.navigation.impl.ResultCreateLoanScreenRouterImpl
import com.example.yarusovashift.navigation.impl.RootScreenRouterImpl
import com.example.yarusovashift.navigation.repository.AllLoanScreenRouter
import com.example.yarusovashift.navigation.repository.AuthScreenRouter
import com.example.yarusovashift.navigation.repository.CreateLoanScreenRouter
import com.example.yarusovashift.navigation.repository.DetailsLoanScreenRouter
import com.example.yarusovashift.navigation.repository.LanguageScreenRouter
import com.example.yarusovashift.navigation.repository.MainLoanScreenRouter
import com.example.yarusovashift.navigation.repository.MenuScreenRouter
import com.example.yarusovashift.navigation.repository.OnboardingScreenRouter
import com.example.yarusovashift.navigation.repository.OptionalScreenRouter
import com.example.yarusovashift.navigation.repository.ResultCreateLoanScreenRouter
import com.example.yarusovashift.navigation.repository.RootScreenRouter
import dagger.Binds
import dagger.Module

@Module
interface RoutersModule {
    @Binds
    fun bindRootScreenRouter(impl: RootScreenRouterImpl): RootScreenRouter

    @Binds
    fun bindAuthScreenRouter(impl: AuthScreenRouterImpl): AuthScreenRouter
    @Binds
    fun bindOnboardingScreenRouter(impl: OnboardingScreenRouterImpl): OnboardingScreenRouter

    @Binds
    fun bindMainLoanScreenRouter(impl: MainLoanScreenRouterImpl): MainLoanScreenRouter

    @Binds
    fun bindAllLoanScreenRouter(impl: AllLoanScreenRouterImpl): AllLoanScreenRouter

    @Binds
    fun bindCreateLoanScreenRouter(impl: CreateLoanScreenRouterImpl): CreateLoanScreenRouter

    @Binds
    fun bindMenuScreenRouter(impl: MenuScreenRouterImpl): MenuScreenRouter

    @Binds
    fun bindDetailsLoanScreenRouter(impl: DetailsLoanScreenRouterImpl): DetailsLoanScreenRouter

    @Binds
    fun bindResultCreateLoanScreenRouter(impl: ResultCreateLoanScreenRouterImpl): ResultCreateLoanScreenRouter

    @Binds
    fun bindOptionalScreenRouter(impl: OptionalScreenRouterImpl): OptionalScreenRouter

    @Binds
    fun bindLanguageScreenRouter(impl: LanguageScreenRouterImpl): LanguageScreenRouter

}
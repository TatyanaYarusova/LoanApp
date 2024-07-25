package com.example.yarusovashift.di.module

import androidx.lifecycle.ViewModel
import com.example.yarusovashift.di.annotation.ViewModelKey
import com.example.yarusovashift.presentation.AllLoanViewModel
import com.example.yarusovashift.presentation.AuthViewModel
import com.example.yarusovashift.presentation.MainLoanViewModel
import com.example.yarusovashift.presentation.MainViewModel
import com.example.yarusovashift.presentation.OnboardingViewModel
import com.example.yarusovashift.presentation.CreateLoanViewModel
import com.example.yarusovashift.presentation.DetailsLoanViewModel
import com.example.yarusovashift.presentation.LanguageViewModel
import com.example.yarusovashift.presentation.MenuViewModel
import com.example.yarusovashift.presentation.OptionalViewModel
import com.example.yarusovashift.presentation.ResultViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    fun bindOnboardingViewModel(viewModel: OnboardingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainLoanViewModel::class)
    fun bindMainLoanViewModel(impl: MainLoanViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateLoanViewModel::class)
    fun bindCreateLoanViewModel(impl: CreateLoanViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllLoanViewModel::class)
    fun bindAllLoanViewModel(impl: AllLoanViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsLoanViewModel::class)
    fun bindDetailsLoanViewModel(impl: DetailsLoanViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    fun bindMenuViewModel(impl: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    fun bindResultViewModel(impl: ResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OptionalViewModel::class)
    fun bindOptionalViewModel(impl: OptionalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LanguageViewModel::class)
    fun bindLanguageViewModel(impl: LanguageViewModel): ViewModel
}
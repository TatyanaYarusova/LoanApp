package com.example.yarusovashift.di.component

import com.example.yarusovashift.ui.AllLoansFragment
import com.example.yarusovashift.ui.AuthFragment
import com.example.yarusovashift.ui.CreateLoanFragment
import com.example.yarusovashift.ui.DetailLoanFragment
import com.example.yarusovashift.ui.LanguageFragment
import com.example.yarusovashift.ui.MainLoanFragment
import com.example.yarusovashift.ui.MenuFragment
import com.example.yarusovashift.ui.OnboardingFragment
import com.example.yarusovashift.ui.OptionalFragment
import com.example.yarusovashift.ui.ResultCreateLoanFragment
import dagger.Subcomponent

@Subcomponent
interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }

    fun inject(fragment: AuthFragment)

    fun inject(fragment: OnboardingFragment)

    fun inject(fragment: MainLoanFragment)

    fun inject(fragment: DetailLoanFragment)

    fun inject(fragment: AllLoansFragment)

    fun inject(fragment: CreateLoanFragment)

    fun inject(fragment: MenuFragment)

    fun inject(fragment: ResultCreateLoanFragment)

    fun inject(fragment: OptionalFragment)

    fun inject(fragment: LanguageFragment)
}
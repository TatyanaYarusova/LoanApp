package com.example.yarusovashift

import android.app.Application
import com.example.yarusovashift.di.component.DaggerLoanAppComponent

class LoanApp : Application() {

    val component by lazy {
        DaggerLoanAppComponent.factory()
            .create(this)
    }
}
package com.example.yarusovashift.di.component

import android.app.Application
import com.example.yarusovashift.LoanApp
import com.example.yarusovashift.di.annotation.AppScope
import com.example.yarusovashift.di.module.DataModule
import com.example.yarusovashift.di.module.NavigationModule
import com.example.yarusovashift.di.module.RoutersModule
import com.example.yarusovashift.di.module.UIModule
import com.example.yarusovashift.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        NavigationModule::class,
        RoutersModule::class,
        UIModule::class
    ]
)
@AppScope
interface LoanAppComponent {

    fun inject(app: LoanApp)

    @Component.Factory
    interface LoanAppComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): LoanAppComponent
    }

    fun activityComponent(): ActivityComponent.Factory


}
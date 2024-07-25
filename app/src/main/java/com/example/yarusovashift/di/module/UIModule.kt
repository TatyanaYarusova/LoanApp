package com.example.yarusovashift.di.module

import android.app.Application
import com.example.yarusovashift.ui.mainAdapter.MainPageAdapter
import dagger.Module
import dagger.Provides

@Module
interface UIModule {
    companion object {
        @Provides
        fun providerAdapter(application: Application) = MainPageAdapter(application)
    }

}
package com.example.yarusovashift.di.module

import com.example.yarusovashift.di.annotation.AppScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
interface NavigationModule {

    companion object {
        @Provides
        @AppScope
        fun provideCicerone(): Cicerone<Router> = Cicerone.create()

        @Provides
        @AppScope
        fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

        @Provides
        @AppScope
        fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()
    }

}
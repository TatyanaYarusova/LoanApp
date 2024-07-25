package com.example.yarusovashift.di.component

import com.example.yarusovashift.ui.MainActivity
import dagger.Subcomponent

@Subcomponent
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun fragmentComponent(): FragmentComponent.Factory

    fun inject(activity: MainActivity)
}
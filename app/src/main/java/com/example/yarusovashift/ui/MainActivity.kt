package com.example.yarusovashift.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.yarusovashift.LoanApp
import com.example.yarusovashift.R
import com.example.yarusovashift.presentation.MainViewModel
import com.example.yarusovashift.presentation.ViewModelFactory
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val component by lazy {
        (application as LoanApp).component
            .activityComponent()
            .create()
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = AppNavigator(this, R.id.fragment_container)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }


    override fun onResume() {
        super.onResume()
        viewModel.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeNavigator()
    }
}
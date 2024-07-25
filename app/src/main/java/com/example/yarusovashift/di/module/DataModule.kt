package com.example.yarusovashift.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.yarusovashift.data.api.ApiFactory
import com.example.yarusovashift.data.api.ApiService
import com.example.yarusovashift.data.db.LoanDao
import com.example.yarusovashift.data.db.LoanDataBase
import com.example.yarusovashift.data.repository.AuthRepositoryImpl
import com.example.yarusovashift.data.repository.LoanRepositoryImpl
import com.example.yarusovashift.di.annotation.AppScope
import com.example.yarusovashift.domain.repository.AuthRepository
import com.example.yarusovashift.domain.repository.LoanRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
interface DataModule {

    @Binds
    @AppScope
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @AppScope
    fun bindLoanRepository(impl: LoanRepositoryImpl): LoanRepository

    companion object {

        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService


        @Provides
        fun provideSharedPreferences(application: Application): SharedPreferences =
            application.getSharedPreferences("token_sp", Context.MODE_PRIVATE)

        @Provides
        fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        fun provideLoanDao(application: Application): LoanDao =
            LoanDataBase.getInstance(application).loanDao()
    }
}
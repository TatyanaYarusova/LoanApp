package com.example.yarusovashift.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL: String =
        "https://shift-loan-app.exodar.heartlessguy.pro/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(ToStringConverterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}
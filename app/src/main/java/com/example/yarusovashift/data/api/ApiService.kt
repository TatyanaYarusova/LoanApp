package com.example.yarusovashift.data.api

import com.example.yarusovashift.data.model.LoanBody
import com.example.yarusovashift.data.model.LoanConditionsDto
import com.example.yarusovashift.data.model.LoanDto
import com.example.yarusovashift.data.model.RegistrationDto
import com.example.yarusovashift.domain.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body loginBody: User
    ): Response<String>

    @POST("registration")
    suspend fun registration(
        @Body registrationBody: User
    ): Response<RegistrationDto>

    @POST("loans")
    suspend fun createLoan(
        @Body loanBody: LoanBody,
        @Header("Authorization") token: String
    ): Response<LoanDto>

    @GET("/loans/{id}")
    suspend fun getLoan(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<LoanDto>

    @GET("loans/all")
    suspend fun getLoanList(
        @Header("Authorization") token: String
    ): Response<List<LoanDto>>

    @GET("loans/conditions")
    suspend fun getLoanConditions(
        @Header("Authorization") token: String
    ): Response<LoanConditionsDto>

}
package com.example.yarusovashift.domain.repository

import com.example.yarusovashift.domain.entity.User
import com.example.yarusovashift.domain.entity.result.RequestResult

interface AuthRepository {

    suspend fun login(loginBody: User): RequestResult<Unit>

    suspend fun registration(registrationBody: User): RequestResult<Unit>

    fun checkLogin(): Boolean

    suspend fun logout()
}
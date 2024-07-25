package com.example.yarusovashift.domain.usecase.auth

import com.example.yarusovashift.domain.entity.User
import com.example.yarusovashift.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(loginBody: User) = authRepository.login(loginBody)
}
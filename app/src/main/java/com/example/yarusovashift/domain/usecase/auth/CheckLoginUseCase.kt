package com.example.yarusovashift.domain.usecase.auth

import com.example.yarusovashift.domain.repository.AuthRepository
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.checkLogin()
}
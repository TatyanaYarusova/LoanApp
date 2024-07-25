package com.example.yarusovashift.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.yarusovashift.data.api.ApiService
import com.example.yarusovashift.data.db.LoanDao
import com.example.yarusovashift.domain.entity.User
import com.example.yarusovashift.domain.entity.result.RequestError
import com.example.yarusovashift.domain.entity.result.RequestResult
import com.example.yarusovashift.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences,
    private val loanDao: LoanDao,
    private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    companion object {
        private const val TOKEN_KEY = "token_key"
    }

    override fun checkLogin(): Boolean {
        return sharedPreferences.contains(TOKEN_KEY)
    }

    override suspend fun login(loginBody: User): RequestResult<Unit> =
        withContext(ioDispatcher) {

            val response = try {
                apiService.login(loginBody)
            } catch (e: Exception) {
                return@withContext exceptionRequest(e, Unit)
            }

            return@withContext if (response.isSuccessful) {
                val userToken = response.body()

                sharedPreferences.edit {
                    putString(TOKEN_KEY, userToken)
                }
                RequestResult.Success(Unit)
            } else when (val code = response.code()) {
                404 -> RequestResult.Error(RequestError.UserNotFound)
                in 500..599 -> RequestResult.Error(RequestError.ServerError(Unit))
                else -> throw RuntimeException("Unknown error code: $code")
            }
        }


    override suspend fun registration(registrationBody: User): RequestResult<Unit> =
        withContext(ioDispatcher) {
            val response = try {
                apiService.registration(registrationBody)
            } catch (e: Exception) {
                return@withContext exceptionRequest(e, Unit)
            }

            return@withContext if (response.isSuccessful) {
                login(registrationBody)
            } else when (val code = response.code()) {
                400 -> RequestResult.Error(RequestError.UserAlreadyExists)
                in 500..599 -> RequestResult.Error(RequestError.ServerError(Unit))
                else -> throw RuntimeException("Unknown error code: $code")
            }

        }

    override suspend fun logout() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
        loanDao.deleteLoans()
    }


    private fun <T> exceptionRequest(e: Exception, cache: T?): RequestResult<T> =
        when (e) {
            is UnknownHostException -> RequestResult.Error(RequestError.NetworkError(cache))
            is SocketTimeoutException -> RequestResult.Error(RequestError.NetworkError(cache))
            is SSLHandshakeException -> RequestResult.Error(RequestError.ServerError(cache))
            else -> throw e
        }
}
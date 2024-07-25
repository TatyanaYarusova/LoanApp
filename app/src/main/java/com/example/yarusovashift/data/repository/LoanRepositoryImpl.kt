package com.example.yarusovashift.data.repository

import android.content.SharedPreferences
import com.example.yarusovashift.data.api.ApiService
import com.example.yarusovashift.data.db.LoanDao
import com.example.yarusovashift.data.mapper.toDbModel
import com.example.yarusovashift.data.mapper.toEntity
import com.example.yarusovashift.data.model.LoanBody
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.domain.entity.result.RequestError
import com.example.yarusovashift.domain.entity.result.RequestResult
import com.example.yarusovashift.domain.repository.LoanRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class LoanRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences,
    private val loanDao: LoanDao,
    private val ioDispatcher: CoroutineDispatcher
) : LoanRepository {

    companion object {
        private const val TOKEN_KEY = "token_key"
    }

    private var _token: String? = sharedPreferences.getString(TOKEN_KEY, null)
    private val token: String
        get() = _token ?: throw RuntimeException("Login first. Token is null.")

    override suspend fun getLoanList(): RequestResult<List<Loan>> =
        withContext(ioDispatcher) {

            val response = try {
                apiService.getLoanList(token)
            } catch (e: Exception) {
                return@withContext exceptionRequest(e, getCache())
            }

            return@withContext if (response.isSuccessful) {
                val loans = response.body() ?: throw RuntimeException("Response body is null")
                loanDao.insertLoanList(loans.map { it.toDbModel() })
                RequestResult.Success(loans.map { it.toEntity() })
            } else when (val code = response.code()) {
                in 500..599 -> RequestResult.Error(RequestError.ServerError(getCache()))
                else -> throw RuntimeException("Unknown error code: $code")
            }

        }

    override suspend fun getLoanConditions(): RequestResult<LoanConditions> =
        withContext(ioDispatcher) {
            val response = try {
                apiService.getLoanConditions(token)
            } catch (e: Exception) {
                return@withContext exceptionRequest(e, null)
            }


            return@withContext if (response.isSuccessful) {
                val loanConditions =
                    response.body() ?: throw RuntimeException("Response body is null")
                RequestResult.Success(loanConditions.toEntity())
            } else when (val code = response.code()) {
                in 500..599 -> RequestResult.Error(RequestError.ServerError(null))
                else -> throw RuntimeException("Unknown error code: $code")
            }

        }

    override suspend fun createLoan(loanBody: LoanBody): RequestResult<Loan> =
        withContext(ioDispatcher) {

            val response = try {
                apiService.createLoan(loanBody, token)
            } catch (e: Exception) {
                return@withContext exceptionRequest(e, null)
            }


            return@withContext if (response.isSuccessful) {
                val loan = response.body() ?: throw RuntimeException("Response body is null")
                loanDao.insertLoan(loan.toDbModel())
                RequestResult.Success(loan.toEntity())
            } else when (val code = response.code()) {
                in 500..599 -> RequestResult.Error(RequestError.ServerError(null))
                else -> throw RuntimeException("Unknown error code: $code")
            }
        }

    override suspend fun getLoan(id: Int): RequestResult<Loan> =
        withContext(ioDispatcher) {

            val response = try {
                apiService.getLoan(id, token)
            } catch (e: Exception) {
                return@withContext exceptionRequest(e, null)
            }


            return@withContext if (response.isSuccessful) {
                val loan = response.body() ?: throw RuntimeException("Response body is null")
                RequestResult.Success(loan.toEntity())
            } else when (val code = response.code()) {
                in 500..599 -> RequestResult.Error(RequestError.ServerError(null))
                else -> throw RuntimeException("Unknown error code: $code")
            }

        }

    private suspend fun getCache() = loanDao.getLoanList().map { it.toEntity() }

    private fun <T> exceptionRequest(e: Exception, cache: T?): RequestResult<T> =
        when (e) {
            is UnknownHostException -> RequestResult.Error(RequestError.NetworkError(cache))
            is SocketTimeoutException -> RequestResult.Error(RequestError.NetworkError(cache))
            is SSLHandshakeException -> RequestResult.Error(RequestError.ServerError(cache))
            else -> throw e
        }
}
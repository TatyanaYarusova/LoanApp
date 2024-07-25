package com.example.yarusovashift.domain.repository

import com.example.yarusovashift.data.model.LoanBody
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.domain.entity.result.RequestResult

interface LoanRepository {

    suspend fun createLoan(loanBody: LoanBody): RequestResult<Loan>

    suspend fun getLoan(id: Int): RequestResult<Loan>

    suspend fun getLoanList(): RequestResult<List<Loan>>

    suspend fun getLoanConditions(): RequestResult<LoanConditions>
}
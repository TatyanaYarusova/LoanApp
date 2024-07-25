package com.example.yarusovashift.domain.usecase.loan

import com.example.yarusovashift.data.model.LoanBody
import com.example.yarusovashift.domain.repository.LoanRepository
import javax.inject.Inject

class CreateLoanUseCase @Inject constructor(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(loanBody: LoanBody) = loanRepository.createLoan(loanBody)
}
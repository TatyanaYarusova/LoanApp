package com.example.yarusovashift.domain.usecase.loan

import com.example.yarusovashift.domain.repository.LoanRepository
import javax.inject.Inject

class GetLoanUseCase @Inject constructor(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(id: Int) = loanRepository.getLoan(id)
}
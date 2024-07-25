package com.example.yarusovashift.domain.usecase.loan

import com.example.yarusovashift.domain.repository.LoanRepository
import javax.inject.Inject

class GetLoanListUseCase @Inject constructor(private val loanRepository: LoanRepository) {
    suspend operator fun invoke() = loanRepository.getLoanList()
}
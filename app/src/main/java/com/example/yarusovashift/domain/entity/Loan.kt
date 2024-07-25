package com.example.yarusovashift.domain.entity

data class Loan(
    val amount: Long,
    val date: String,
    val id: Long,
    val state: LoanState,

    val percent: Double,
    val period: Int,

    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
)



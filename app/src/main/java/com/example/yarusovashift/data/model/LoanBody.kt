package com.example.yarusovashift.data.model

data class LoanBody(
    val amount: Long,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String
)

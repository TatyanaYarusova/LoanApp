package com.example.yarusovashift.data.model

import com.google.gson.annotations.SerializedName

data class LoanDto (
    @SerializedName("amount")
    val amount: Long,

    @SerializedName("date")
    val date: String,

    @SerializedName("id")
    val id: Long,

    @SerializedName("state")
    val state: String,

    @SerializedName("percent")
    val percent: Double,

    @SerializedName("period")
    val period: Int,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String,
)
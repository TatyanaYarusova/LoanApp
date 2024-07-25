package com.example.yarusovashift.data.model

import com.google.gson.annotations.SerializedName

data class LoanConditionsDto(
    @SerializedName("maxAmount")
    val maxAmount: Number,

    @SerializedName("percent")
    val percent: Double,

    @SerializedName("period")
    val period: Int
)

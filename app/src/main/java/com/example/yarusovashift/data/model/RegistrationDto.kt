package com.example.yarusovashift.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationDto (
    @SerializedName("name")
    val name: String,

    @SerializedName("role")
    val role: String
)




package com.example.yarusovashift.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loans_table")
data class LoanDBModel(
    @PrimaryKey
    val id: Long,
    val amount: Long,
    val date: String,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: String
)

package com.example.yarusovashift.data.mapper

import android.os.Parcelable
import com.example.yarusovashift.data.db.model.LoanDBModel
import com.example.yarusovashift.data.model.LoanConditionsDto
import com.example.yarusovashift.data.model.LoanDto
import com.example.yarusovashift.domain.entity.Loan
import com.example.yarusovashift.domain.entity.LoanConditions
import com.example.yarusovashift.domain.entity.LoanState
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Locale

fun LoanDto.toEntity() = Loan(
    amount = amount,
    date = date.convertFormatDate(),
    id = id,
    state = convertLoanStatusToEnum(state),

    percent = percent,
    period = period,

    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
)

fun LoanConditionsDto.toEntity() = LoanConditions(
    maxAmount = maxAmount,
    percent = percent,
    period = period
)

fun LoanDto.toDbModel() = LoanDBModel(
    id = id,
    amount = amount,
    date = date,
    firstName = firstName,
    lastName = lastName,
    percent = percent,
    period = period,
    phoneNumber = phoneNumber,
    state = state
)

fun LoanDBModel.toEntity() = Loan(
    id = id,
    amount = amount,
    date = date.convertFormatDate(),
    firstName = firstName,
    lastName = lastName,
    percent = percent,
    period = period,
    phoneNumber = phoneNumber,
    state = convertLoanStatusToEnum(state)
)


fun String.convertFormatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM, EEE", Locale("ru", "RU"))

    val date = inputFormat.parse(this)
    return date?.let { outputFormat.format(it) } ?: ""
}

fun convertLoanStatusToEnum(status: String) =
    when (status) {
        "APPROVED" -> LoanState.APPROVED
        "REGISTERED" -> LoanState.REGISTERED
        "REJECTED" -> LoanState.REJECTED
        else -> throw RuntimeException("Unknown loan state")
    }


@Parcelize
data class LoanParcelableModel(
    val id: Long,
    val amount: Long,
    val date: String,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: LoanState
) : Parcelable


fun LoanParcelableModel.toEntity() = Loan(
    id = id,
    amount = amount,
    date = date,
    firstName = firstName,
    lastName = lastName,
    percent = percent,
    period = period,
    phoneNumber = phoneNumber,
    state = state
)

fun Loan.toParcelableModel() = LoanParcelableModel(
    id = id,
    amount = amount,
    date = date,
    firstName = firstName,
    lastName = lastName,
    percent = percent,
    period = period,
    phoneNumber = phoneNumber,
    state = state
)

@Parcelize
data class ConditionLoanParcelableModel(
    val maxAmount: Number,
    val percent: Double,
    val period: Int
) : Parcelable

fun ConditionLoanParcelableModel.toEntity() = LoanConditions(
    maxAmount = maxAmount,
    percent = percent,
    period = period
)

fun LoanConditions.toParcelableModel() = ConditionLoanParcelableModel(
    maxAmount = maxAmount,
    percent = percent,
    period = period
)

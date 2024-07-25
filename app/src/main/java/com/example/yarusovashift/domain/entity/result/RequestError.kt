package com.example.yarusovashift.domain.entity.result

sealed class RequestError<out T> {
    object UserNotFound : RequestError<Nothing>()

    object UserAlreadyExists : RequestError<Nothing>()

    data class ServerError<T>(val cache: T?) : RequestError<T>()

    data class NetworkError<T>(val cache: T?) : RequestError<T>()

}
package com.example.yarusovashift.presentation.state

sealed class ScreenState<out T> {

    object Loading : ScreenState<Nothing>()

    data class Content<T>(val content: T) : ScreenState<T>()

    object Error : ScreenState<Nothing>()

    object Success : ScreenState<Nothing>()

}
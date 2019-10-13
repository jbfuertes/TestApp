package com.appetiser.testapp.presenter.common.state

sealed class LiveState<out T> {

    object Loading : LiveState<Nothing>()

    data class Data<T>(val data: T) : LiveState<T>()

    data class Error<T>(val error: Throwable) : LiveState<T>()

}
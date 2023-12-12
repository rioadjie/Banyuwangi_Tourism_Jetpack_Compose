package com.md12.rio.banyuwangitourism.utils.interface_utils

sealed class StateInterface<out T: Any?> {
    object Loading : StateInterface<Nothing>()
    data class Success<out T: Any>(val data: T) : StateInterface<T>()
    data class Error(val message: String) : StateInterface<Nothing>()
}
package com.example.denettestapp.data.common

sealed class DataState<out R> {
    object Init : DataState<Nothing>()
    data class Data<out T>(val data: T) : DataState<T>()
    data class Error(val error: String) : DataState<Nothing>()
//    object Loading : DataState<Nothing>()
}
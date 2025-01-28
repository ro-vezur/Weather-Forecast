package com.example.weatherforecast.core.data

sealed class ResultResource<T>(val data: T?, val message: String? = null) {
    class Loading<T>(data: T? = null): ResultResource<T>(data)
    class Success<T>(data: T): ResultResource<T>(data)
    class Error<T>(message: String, data: T? = null): ResultResource<T>(data,message)
}
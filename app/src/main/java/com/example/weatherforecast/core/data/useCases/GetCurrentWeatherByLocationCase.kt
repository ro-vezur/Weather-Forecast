package com.example.weatherforecast.core.data.useCases

import com.example.weatherforecast.core.data.ResultResource
import com.example.weatherforecast.core.data.api.ApiInterface
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentWeatherByLocationCase @Inject constructor(
    private val apiInterface: ApiInterface
) {
    operator fun invoke(location: String) = flow {
        emit(ResultResource.Loading())

        emit(ResultResource.Success(data = apiInterface.currentWeather(location)))
    }.catch { e ->
        emit(ResultResource.Error(message = e.message.toString()))
    }

}
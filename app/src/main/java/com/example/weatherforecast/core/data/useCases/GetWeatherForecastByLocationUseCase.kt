package com.example.weatherforecast.core.data.useCases

import com.example.weatherforecast.core.data.ResultResource
import com.example.weatherforecast.core.data.api.ApiInterface
import com.example.weatherforecast.core.data.dtos.forecastWeather.WeatherForecast
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherForecastByLocationUseCase @Inject constructor(
    private val apiService: ApiInterface
) {
    suspend operator fun invoke(location: String, days: Int) = flow {
        emit(ResultResource.Loading())

        emit(ResultResource.Success(apiService.weatherForecast(location, days)))
    }.catch { e ->
        emit(ResultResource.Error(e.message.toString()))
    }
}
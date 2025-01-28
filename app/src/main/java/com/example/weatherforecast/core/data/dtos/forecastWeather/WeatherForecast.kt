package com.example.weatherforecast.core.data.dtos.forecastWeather

import com.example.weatherforecast.core.data.dtos.currentWeather.Location
import com.example.weatherforecast.core.data.dtos.currentWeather.CurrentDay
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecast(
    val location: Location,
    val current: CurrentDay,
    val forecast: ForecastData
)
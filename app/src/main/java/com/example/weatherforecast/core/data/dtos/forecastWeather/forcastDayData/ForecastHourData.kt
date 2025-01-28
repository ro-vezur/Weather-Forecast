package com.example.weatherforecast.core.data.dtos.forecastWeather.forcastDayData

import com.example.weatherforecast.core.data.dtos.weather.Condition
import kotlinx.serialization.Serializable

@Serializable
data class ForecastHourData(
    val time: String,
    val temp_c: Double,
    val condition: Condition,
)
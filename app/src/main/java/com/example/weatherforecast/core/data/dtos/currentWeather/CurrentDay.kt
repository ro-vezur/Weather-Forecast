package com.example.weatherforecast.core.data.dtos.currentWeather

import com.example.weatherforecast.core.data.dtos.weather.Condition
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDay(
    val temp_c: Double,
    val condition: Condition,
    val wind_kph: Double,
    val humidity: Int,
)
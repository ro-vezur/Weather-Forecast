package com.example.weatherforecast.core.data.dtos.currentWeather

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    val location: Location,
    val current: CurrentDay
)
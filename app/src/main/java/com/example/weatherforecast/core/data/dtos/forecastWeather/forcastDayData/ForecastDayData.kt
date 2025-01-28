package com.example.weatherforecast.core.data.dtos.forecastWeather.forcastDayData

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayData(
    val date: String,
    val day: ForecastDay,
    val hour: List<ForecastHourData>
)

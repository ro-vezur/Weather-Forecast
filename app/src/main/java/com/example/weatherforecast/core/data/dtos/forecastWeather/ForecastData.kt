package com.example.weatherforecast.core.data.dtos.forecastWeather

import com.example.weatherforecast.core.data.dtos.forecastWeather.forcastDayData.ForecastDayData
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastData(
    @SerializedName("forecastday") val daysForecast: List<ForecastDayData>
)
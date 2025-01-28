package com.example.weatherforecast.core.data.dtos.forecastWeather.forcastDayData

import com.example.weatherforecast.core.data.dtos.weather.Condition
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    val avgtemp_c: Double,
    val condition: Condition,
    @SerializedName("avghumidity") val avgHumidity: Double,

)
package com.example.weatherforecast.core.data.dtos.currentWeather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val region: String,
    val lat: Double,
    val lon: Double,
    @SerializedName("localtime_epoch") val localTimeMillis: Long,
    @SerializedName("localtime") val localTime: String,
)
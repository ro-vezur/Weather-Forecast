package com.example.weatherforecast.core.data.dtos.weather

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val text: String,
    val icon: String,
)
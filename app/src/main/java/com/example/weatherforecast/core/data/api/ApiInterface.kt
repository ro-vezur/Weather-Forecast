package com.example.weatherforecast.core.data.api

import com.example.weatherforecast.core.data.dtos.currentWeather.CurrentWeather
import com.example.weatherforecast.core.data.dtos.forecastWeather.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("current.json?")
    suspend fun currentWeather(
        @Query("q") location: String,
    ): CurrentWeather

    @GET("forecast.json?")
    suspend fun weatherForecast(
        @Query("q") location: String,
        @Query("days") days: Int,
    ): WeatherForecast

}
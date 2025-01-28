package com.example.weatherforecast.core.di

import com.example.weatherforecast.core.data.api.ApiInterface
import com.example.weatherforecast.core.data.dtos.forecastWeather.WeatherForecast
import com.example.weatherforecast.core.data.useCases.GetCurrentWeatherByLocationCase
import com.example.weatherforecast.core.data.useCases.GetWeatherForecastByLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCases {

    @Provides
    @Singleton
    fun provideGetCurrentWeatherByNameUseCase(apiService: ApiInterface): GetCurrentWeatherByLocationCase {
        return GetCurrentWeatherByLocationCase(apiService)
    }

    @Provides
    @Singleton
    fun provideGetWeatherForecastByLocationUseCase(apiService: ApiInterface): GetWeatherForecastByLocationUseCase {
        return GetWeatherForecastByLocationUseCase(apiService)
    }

}
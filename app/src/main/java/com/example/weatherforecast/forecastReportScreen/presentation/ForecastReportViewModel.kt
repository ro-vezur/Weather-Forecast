package com.example.weatherforecast.forecastReportScreen.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.core.data.ResultResource
import com.example.weatherforecast.core.data.dtos.forecastWeather.WeatherForecast
import com.example.weatherforecast.core.data.dtos.forecastWeather.forcastDayData.ForecastDayData
import com.example.weatherforecast.core.data.useCases.GetWeatherForecastByLocationUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel(assistedFactory = ForecastReportViewModel.Factory::class)
class ForecastReportViewModel @AssistedInject constructor(
    @Assisted val location: String,
    private val getWeatherForecastByLocationUseCase: GetWeatherForecastByLocationUseCase
): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(location: String): ForecastReportViewModel
    }

    val weatherForecast = MutableLiveData<ResultResource<WeatherForecast>>()
    val selectedDay = MutableLiveData<ForecastDayData>()

    init {
        setWeatherForecast()
    }

    fun setWeatherForecast() = viewModelScope.launch {
        getWeatherForecastByLocationUseCase(location,7).collectLatest { result ->
            weatherForecast.value = result
        }
    }

    fun setDay(forecastDayData: ForecastDayData) {
        selectedDay.value = forecastDayData
    }
}
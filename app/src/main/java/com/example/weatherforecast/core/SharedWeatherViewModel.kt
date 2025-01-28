package com.example.weatherforecast.core

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.core.data.ResultResource
import com.example.weatherforecast.core.data.dtos.currentWeather.CurrentWeather
import com.example.weatherforecast.core.data.useCases.GetCurrentWeatherByLocationCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedWeatherViewModel @Inject constructor(
   private val getCurrentWeatherByName: GetCurrentWeatherByLocationCase
): ViewModel() {

    private val currentLocation = MutableLiveData<String>()
    val currentWeather = MutableLiveData<ResultResource<CurrentWeather>>()

    init {
        viewModelScope.launch {
            while (true) {
                setCurrentWeatherByName(currentLocation.value?: "")
                delay(60*1000)
            }
        }
    }

    fun setCurrentWeatherByName(location: String) = viewModelScope.launch {
        getCurrentWeatherByName.invoke(location).collectLatest { result ->
            currentWeather.value = result
        }
    }

    fun setCurrentLocation(location: String) {
        currentLocation.value = location
    }

    fun getCurrentLocation(): String = currentLocation.value?: ""

}


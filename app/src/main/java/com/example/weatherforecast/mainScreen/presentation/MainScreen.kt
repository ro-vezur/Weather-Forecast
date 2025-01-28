package com.example.weatherforecast.mainScreen.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.core.data.ResultResource
import com.example.weatherforecast.databinding.FragmentMainScreenBinding
import com.example.weatherforecast.core.SharedWeatherViewModel
import com.example.weatherforecast.utlis.TimesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var navController: NavController

    private val mainScreenViewModel: SharedWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentMainScreenBinding.inflate(layoutInflater,container,false)

        navController = findNavController()

        binding.btnWeatherForecast.setOnClickListener {
            val action = MainScreenDirections.actionMainFragmentToForecastReportFragment(mainScreenViewModel.getCurrentLocation())
            navController.navigate(action)
        }

        observeCurrentWeather()

        return binding.root
    }

    private fun observeCurrentWeather() {
        mainScreenViewModel.currentWeather.observe(viewLifecycleOwner) { result ->
            when(result) {
                is ResultResource.Loading -> {

                }
                is ResultResource.Success -> {
                    result.data?.let { currentWeather ->

                        Glide.with(binding.imageWeatherCondition.context)
                            .load("https:${currentWeather.current.condition.icon}")
                            .into(binding.imageWeatherCondition)

                        binding.tvCity.text = currentWeather.location.name

                        binding.tvDate.text = "Today, " +
                                TimesHelper.getDayOfMonth() +
                                " ${TimesHelper.getMonthNameFromMilliseconds(currentWeather.location.localTimeMillis)}"

                        binding.tvTemp.text = " ${currentWeather.current.temp_c}°"
                        binding.tvWeatherCondition.text = currentWeather.current.condition.text

                        binding.tvWindSpeed.text = "${currentWeather.current.wind_kph} km/h"
                        binding.tvHumidityPercentage.text = "${currentWeather.current.humidity} %"
                    }
                }
                is ResultResource.Error -> {
                    Log.d("error",result.message.toString())
                }
            }
        }
    }


}
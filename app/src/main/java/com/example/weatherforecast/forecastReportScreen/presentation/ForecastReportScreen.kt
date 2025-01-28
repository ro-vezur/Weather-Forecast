package com.example.weatherforecast.forecastReportScreen.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.weatherforecast.core.data.ResultResource
import com.example.weatherforecast.databinding.FragmentForecastReportBinding
import com.example.weatherforecast.forecastReportScreen.presentation.adapters.DaysForecastAdapter
import com.example.weatherforecast.forecastReportScreen.presentation.adapters.HoursForecastAdapter
import com.example.weatherforecast.utlis.TimesHelper
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback

@AndroidEntryPoint
class ForecastReportScreen : Fragment() {

    private lateinit var binding: FragmentForecastReportBinding
    private lateinit var navController: NavController
    private lateinit var location: String

    private val forecastReportViewModel: ForecastReportViewModel by viewModels(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<ForecastReportViewModel.Factory> { factory ->
                factory.create(location)
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentForecastReportBinding.inflate(layoutInflater,container,false)

        val args: ForecastReportScreenArgs by navArgs()

        location = args.location

        navController = findNavController()

        binding.btnTurnBack.setOnClickListener {
            navController.navigateUp()
        }

        observeWeatherForecast()
        observeSelectedDayForecast()

        return binding.root
    }

    private fun observeWeatherForecast() {
        forecastReportViewModel.weatherForecast.observe(viewLifecycleOwner) { result ->
            when(result) {
                is ResultResource.Loading -> {

                }
                is ResultResource.Success -> {
                    result.data?.let { data ->
                        val adapter = DaysForecastAdapter(data.forecast)

                        adapter.itemClick = { forecastDay ->
                            forecastReportViewModel.setDay(forecastDay)
                        }

                        binding.recyclerViewDaysForecast.layoutManager = LinearLayoutManager(requireContext())
                        binding.recyclerViewDaysForecast.adapter = adapter

                        forecastReportViewModel.setDay(data.forecast.daysForecast[0])
                    }
                }
                is ResultResource.Error -> {

                }
            }
        }
    }

    private fun observeSelectedDayForecast() {
        forecastReportViewModel.selectedDay.observe(viewLifecycleOwner) { day ->

            val horizontalLayoutManager = LinearLayoutManager(
                requireContext(),LinearLayoutManager.HORIZONTAL,false
            )

            val adapter = HoursForecastAdapter(day.hour)

            binding.recyclerViewHoursDayForecast.layoutManager = horizontalLayoutManager
            binding.recyclerViewHoursDayForecast.adapter = adapter

            binding.tvDate.text = TimesHelper.getMonthNameFromDate(day.date).take(3) +
                    ", ${TimesHelper.getDayNumberFromDate(day.date)}"
        }
    }

}
package com.example.weatherforecast.forecastReportScreen.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecast.core.data.dtos.forecastWeather.ForecastData
import com.example.weatherforecast.core.data.dtos.forecastWeather.forcastDayData.ForecastDay
import com.example.weatherforecast.core.data.dtos.forecastWeather.forcastDayData.ForecastDayData
import com.example.weatherforecast.databinding.ItemDayForecastBinding
import com.example.weatherforecast.utlis.TimesHelper

class DaysForecastAdapter(private val days: ForecastData): RecyclerView.Adapter<DaysForecastAdapter.DaysForecastViewHolder>() {

    var itemClick: ((forecastDay: ForecastDayData) -> Unit)? = null

    class DaysForecastViewHolder(val binding: ItemDayForecastBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDayForecastBinding.inflate(inflater,parent,false)

        return DaysForecastViewHolder(binding)
    }

    override fun getItemCount(): Int = days.daysForecast.size

    override fun onBindViewHolder(holder: DaysForecastViewHolder, position: Int) {
        val day = days.daysForecast[position]
        val context = holder.itemView.context

        with(holder.binding) {

            root.setOnClickListener {
                itemClick?.invoke(day)
            }

            tvDate.text = TimesHelper.getMonthNameFromDate(day.date).take(3) +
                    ", ${TimesHelper.getDayNumberFromDate(day.date)}"

            Glide.with(context)
                .load("https:${day.day.condition.icon}")
                .into(imageWeatherCondition)

            tvTemp.text = "${day.day.avgtemp_c}°"
        }

    }
}
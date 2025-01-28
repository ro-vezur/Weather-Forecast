package com.example.weatherforecast.forecastReportScreen.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecast.core.data.dtos.forecastWeather.forcastDayData.ForecastHourData
import com.example.weatherforecast.databinding.ItemHourForecastBinding
import com.example.weatherforecast.utlis.TimesHelper

class HoursForecastAdapter(
    val hoursDayData: List<ForecastHourData>
): RecyclerView.Adapter<HoursForecastAdapter.HoursForecastViewHolder>() {

    class HoursForecastViewHolder(val binding: ItemHourForecastBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHourForecastBinding.inflate(inflater,parent,false)

        return HoursForecastViewHolder(binding)
    }

    override fun getItemCount(): Int = hoursDayData.size

    override fun onBindViewHolder(holder: HoursForecastViewHolder, position: Int) {
        val hourData = hoursDayData[position]
        val context = holder.itemView.context

        with(holder.binding) {
            holder.binding.root.background
            tvTemp.text = "${hourData.temp_c}℃"

            Glide.with(context)
                .load("https:${hourData.condition.icon}")
                .into(imageWeatherCondition)

            tvHour.text = TimesHelper.getHourAndMinutes(hourData.time)
        }
    }
}
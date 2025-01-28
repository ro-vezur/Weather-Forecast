package com.example.weatherforecast.utlis

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

object TimesHelper {

    private val calender = Calendar.getInstance()

    fun getMonthNameFromMilliseconds(millis: Long): String {
        val instant = Instant.ofEpochMilli(millis)

        val dateTime = instant.atZone(ZoneId.systemDefault())

        return dateTime.month.getDisplayName(TextStyle.FULL, Locale.US)
    }

    fun getHourAndMinutes(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val dateTime = LocalDateTime.parse(dateString, formatter)
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    fun getMonthNameFromDate(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)
        return date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) // e.g., "January"
    }

    fun getDayNumberFromDate(dateString: String): Int {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)
        return date.dayOfMonth
    }

    fun getDayOfMonth(): Int {
        return calender.get(Calendar.DAY_OF_MONTH)
    }
}
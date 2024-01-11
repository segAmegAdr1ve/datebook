package com.example.datebook.viewModel

import android.icu.text.DateFormat
import android.text.format.DateUtils
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class DatebookViewModel: ViewModel() {

    private var calendar = Calendar.getInstance(Locale.getDefault())

    fun formatDate(): String {
        val outputDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())//.apply {
        return outputDateFormat.format(calendar.time.time)
    }

    fun formatTimeOfDay(): String {
        val outputDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return outputDateFormat.format(calendar.time.time)
    }

    fun setDate(date: Long) {
        val temporaryCalendar = Calendar.Builder().setInstant(date).build()
        calendar.set(
            temporaryCalendar.get(Calendar.YEAR),
            temporaryCalendar.get(Calendar.MONTH),
            temporaryCalendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun setTimeOfDay(hour: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
    }
}
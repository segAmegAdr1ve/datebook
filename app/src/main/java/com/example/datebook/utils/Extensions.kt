package com.example.datebook.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun Long.toNormalDateFormat(): String {
    val outputDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return outputDateFormat.format(this)
}

fun Long.toNormalTimeFormat(): String {
    val outputDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return outputDateFormat.format(this)
}
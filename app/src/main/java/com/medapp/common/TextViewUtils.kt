package com.medapp.common

import android.widget.TextView
import java.util.Calendar
import java.util.GregorianCalendar

fun TextView.showGreetings(username: String) {
    val hour = GregorianCalendar().get(Calendar.HOUR_OF_DAY)
    val greetings = when (hour) {
        in 5..12 -> "Good morning"
        in 12..18 -> "Good afternoon"
        else -> "Good evening"
    }
    text = greetings + ",\n" + username
}
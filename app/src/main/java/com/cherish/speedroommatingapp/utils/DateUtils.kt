package com.cherish.speedroommatingapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
@SuppressLint("NewApi")
class DateUtils {
    companion object {
        fun addOrdinanceToDate(n : Int) : String {
            require(!(n < 1 || n > 31)) { "Illegal day of month" }

            if (n in 11..13) {
                return "th"
            }

            return when (n % 10) {
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }
        }

        fun formatDate(date : String) : String {
            val zone = ZonedDateTime.parse(date)
            val dayFormat = DateTimeFormatter.ofPattern("d")
            val monthFormat  = DateTimeFormatter.ofPattern(" MMMM")
            return dayFormat.format(zone) + addOrdinanceToDate(zone.dayOfMonth) + monthFormat.format(zone)
        }


        fun formatDateOldApi(mDate: String) : String{
            val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val dayFormat = SimpleDateFormat("d", Locale.getDefault())
            val monthFormat = SimpleDateFormat(" MMMM", Locale.getDefault())
            val date  = zonedFormat.parse(mDate)
            val newDate = dayFormat.format(date!!)
            val newMonth = monthFormat.format(date)
            return newDate + addOrdinanceToDate(newDate.toInt()) + newMonth
        }


        fun formatTimeNewApi(starTime : String, endTime: String) : String{
            val zone = ZonedDateTime.parse(endTime)
            val start = ZonedDateTime.parse(starTime)
            val timeFormat  = DateTimeFormatter.ofPattern("h:mm a")
            val formatStartTime = DateTimeFormatter.ofPattern("h:mm")
            val newTime = timeFormat.format(zone)
            val result = formatStartTime.format(start)
            return "$result \u2014 $newTime"


        }

        fun formatTimeOldApi(starTime : String, endTime : String) : String{
            val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val formatter = SimpleDateFormat("h :mm a", Locale.getDefault())
            val startFormat = SimpleDateFormat("h :mm ", Locale.getDefault())
            val date  = zonedFormat.parse(endTime)
            val startDate = startFormat.parse(starTime)
            val result = formatter.format(date!!)
            val red = formatter.format(startDate!!)
            return "$red \u2014 $result"

        }
    }


}
package com.cherish.speedroommatingapp.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
@SuppressLint("NewApi")
class DateUtils {
    companion object {
        fun addOrdinanceToDate(n : Int) : String {
            if (n < 1 || n > 31) {
                throw IllegalArgumentException("Illegal day of month")
            }

            if (n >= 11 && n <= 13) {
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
            val dayff = DateTimeFormatter.ofPattern("d")
            val month  = DateTimeFormatter.ofPattern(" MMMM")
            val jjjj = dayff.format(zone) + addOrdinanceToDate(zone.dayOfMonth) + month.format(zone)
            var day = zone.dayOfMonth
            Log.i("hhhh", day.toString())
            System.out.println(jjjj)
            return  jjjj
        }


        fun formatDateOldApi(mDate: String) : String{
            val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val day = SimpleDateFormat("d", Locale.getDefault())
            val month = SimpleDateFormat(" MMMM", Locale.getDefault())
            val formatter = SimpleDateFormat("dd MMM", Locale.getDefault())
            val date  = zonedFormat.parse(mDate)
            val answer = formatter.format(date!!)
            val newAnswer = day.format(date)
            val monthAnser = month.format(date)
            val answerr = newAnswer + addOrdinanceToDate(newAnswer.toInt()) + monthAnser
            Log.i("NEWWWWW", answerr)
            return answerr
        }


        fun formatTimeNewApi(startime : String, endtime: String) : String{
            var zone = ZonedDateTime.parse(endtime)
            val  start = ZonedDateTime.parse(startime)
            var hhh  = DateTimeFormatter.ofPattern("h:mm a")
            var formatStartTime = DateTimeFormatter.ofPattern("h:mm")
            var jjjj = hhh.format(zone)
            val result = formatStartTime.format(start)
            return "$result \u2014 $jjjj"


        }

        fun formatTimeOldApi(startime : String, endtime : String) : String{
            val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val formatter = SimpleDateFormat("h :mm a", Locale.getDefault())
            val startFomatter = SimpleDateFormat("h :mm ", Locale.getDefault())
            val date  = zonedFormat.parse(endtime)
            val startdate = startFomatter.parse(startime)
            var result = formatter.format(date!!)
            var red = formatter.format(startdate!!)
            return "$red \u2014 $result"

        }
    }







//               val formatter =  SimpleDateFormat("EEE, dd MMM yyyy");
//               val today = formatter.format(item.end_time)
//                System.out.println("Today : " + today)
//                var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.US)
//var formatter =  SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US)
//var output = formatter.format(parser.parse(item.end_time!!).time!!)
//                Log.i("OUT", output)



/// ***************DATE****************






//                fun ApplyOrdinal( format : String, day : Int) : String{
//                    require(!(day < 1 || day > 31)) { String.format("Bad day of month (%s)", day) }
//                    val  ord = when (day) {
//                        1, 21, 31 -> "st"
//                        2, 22 -> "nd"
//                        3, 23 -> "rd"
//                        else -> "th"
//                    }
//                    Log.i("FORAMT",String.format(format, day, ord))
//                    return String.format(format, day, ord)
//
//
//                }

//
//                var zone = ZonedDateTime.parse(item.end_time!!)
//                var dayff = DateTimeFormatter.ofPattern("d")
//                var month  = DateTimeFormatter.ofPattern(" MMMM")
//                var jjjj = dayff.format(zone) + AppyOrdinance(zone.dayOfMonth) + month.format(zone)
//                var day = zone.getDayOfMonth()
//                Log.i("hhhh", day.toString())
//                System.out.println(jjjj)
//
//
//                var date = AppyOrdinance(day)
//                Log.i("DATE", date)
//  ***************  TIME    **********************

//                var zone = ZonedDateTime.parse(item.end_time)
//                var hhh  = DateTimeFormatter.ofPattern("hh:mm a")
//
//	             var jjjj = hhh.format(zone)
//	             System.out.println(jjjj)








//                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
//                    val answer: String =  current.format(formatter)
//                    Log.d("answer",answer)
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//                    val date = LocalDate.parse(item.end_time , firstApiFormat)
//                    Log.i("PARSER", date.toString())
//
//                } else {










//        ***********  Time ***********
//
//                val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
//                    val formatter = SimpleDateFormat("hh :mm a", Locale.getDefault())
//                val date  = zonedFormat.parse(item.end_time!!)
//                    val answer = formatter.format(date!!)
//                    Log.d("OLD",answer.toString())






//                }




//                val firstApiFormat = DateTimeFormatter.fp("yyyy-MM-dd HH:mm:ss")
//                val date = LocalDate.parse("2019-08-07 09:00:00" , firstApiFormat)
    //                .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                .get()


    //        val image : RelativeLayout? = itemView.image
//        val cost: Button  = itemView.cost
//        val location: TextView = itemView.location
//        val date :TextView = itemView.date
//        val time : TextView = itemView.time
//        val venue: TextView = itemView.venue

}
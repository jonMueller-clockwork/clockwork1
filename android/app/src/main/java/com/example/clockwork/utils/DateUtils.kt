package com.example.clockwork.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// TODO: convert to LocalDateTime when min API >= 26
class DateUtils {
    companion object {
        fun time(hour: Int, minute: Int = 0, second: Int = 0, millisecond: Int = 0): Date {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, second)
            calendar.set(Calendar.MILLISECOND, millisecond)

            return calendar.time
        }

        // string formatting
        fun formatTime(date: Date): String {
            val formatter = SimpleDateFormat("h:mma", Locale.getDefault())
            val formattedTime = formatter.format(date)

            return formattedTime.replace("AM", "a").replace("PM", "p")
        }

        private fun shortMonthDay(date: Date): String {
            val dateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
            return dateFormat.format(date)
        }

        fun shortMonthDay(): String {
            return shortMonthDay(Date())
        }

        // calculations
        fun hoursBetween(startDate: Date, endDate: Date): Float {
            val differenceInMillis = endDate.time - startDate.time
            return differenceInMillis.toFloat() / (1000 * 60 * 60)
        }

        fun minutesBetween(startDate: Date, endDate: Date): Int {
            val differenceInMillis = endDate.time - startDate.time
            return differenceInMillis.toInt() / (1000 * 60) // TODO: validate in int range
        }

        fun isBetween(date: Date, startDate: Date, endDate: Date): Boolean {
            return date.after(startDate) && date.before(endDate)
        }

        fun isDateInRange(date: Date, startDate: Date, endDate: Date): Boolean {
            return date.after(startDate) && date.before(endDate) || date == startDate || date == endDate
        }

        fun percentCompleted(date: Date, startDate: Date, endDate: Date): Float {
            assert(isDateInRange(date, startDate, endDate))
            return (date.time - startDate.time).toFloat() / (endDate.time - startDate.time).toFloat()
        }

        // date creation
        fun minutesFromNow(minutes: Int): Date {
            val currentTime = Date()
            val oneMinuteInMillis = 60 * 1000

            return Date(currentTime.time + oneMinuteInMillis * minutes)
        }

        fun hoursFromNow(hours: Int): Date {
            return minutesFromNow(hours * 60)
        }
    }
}
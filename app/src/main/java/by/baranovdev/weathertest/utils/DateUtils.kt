package by.baranovdev.weathertest.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun isTomorrow(date: Date): Boolean {
        val calendar = Calendar.getInstance()
        val today = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time

        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val todayStr = dateFormat.format(today)
        val tomorrowStr = dateFormat.format(tomorrow)
        val dateStr = dateFormat.format(date)

        return dateStr == tomorrowStr && todayStr != tomorrowStr
    }
}
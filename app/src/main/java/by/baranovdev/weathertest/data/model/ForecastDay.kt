package by.baranovdev.weathertest.data.model

data class ForecastDay(
    val date: String,
    val maxTemperature: Double,
    val minTemperature: Double,
    val icon: String,
    val day: String?
)
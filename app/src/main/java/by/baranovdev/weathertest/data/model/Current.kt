package by.baranovdev.weathertest.data.model

data class Current(
    val uv: String? = null,
    val condition: Condition? = null,
    val windMph: Double? = null,
    val humidity: Int? = null,
    val tempF: Double? = null
)
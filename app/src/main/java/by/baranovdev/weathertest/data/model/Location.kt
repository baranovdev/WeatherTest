package by.baranovdev.weathertest.data.model

data class Location(
    val localtime: String,
    val country: String,
    val localtimeEpoch: Int,
    val name: String,
    val lon: String?,
    val region: String,
    val lat: String,
    val tzId: String,
    val time: String
)
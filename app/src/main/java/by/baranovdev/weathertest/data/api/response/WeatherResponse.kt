package by.baranovdev.weathertest.data.api.response

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
	@SerializedName("current")
	val current: CurrentResponse? = null,
	@SerializedName("location")
	val location: LocationResponse? = null
)

data class ConditionResponse(
	@SerializedName("icon")
	val icon: String? = null,
	@SerializedName("text")
	val text: String? = null
)

data class CurrentResponse(
	@SerializedName("uv")
	val uv: Double? = null,
	@SerializedName("condition")
	val condition: ConditionResponse? = null,
	@SerializedName("wind_mph")
	val windMph: Double? = null,
	@SerializedName("humidity")
	val humidity: Int? = null,
	@SerializedName("temp_f")
	val tempF: Double? = null
)

data class LocationResponse(
	@SerializedName("localtime")
	val localtime: String? = null,
	@SerializedName("country")
	val country: String? = null,
	@SerializedName("localtime_epoch")
	val localtimeEpoch: Int? = null,
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("lon")
	val lon: Double? = null,
	@SerializedName("region")
	val region: String? = null,
	@SerializedName("lat")
	val lat: Double? = null,
	@SerializedName("tz_id")
	val tzId: String? = null
)


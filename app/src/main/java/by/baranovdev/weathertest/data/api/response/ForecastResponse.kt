package by.baranovdev.weathertest.data.api.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
	@SerializedName("forecast")
	val forecast: ForecastElement? = null
)

data class ForecastElement(
	@SerializedName("forecastday")
	val forecastday: List<ForecastDayItem?>? = null
)


data class ForecastDayItem(

	@SerializedName("date")
	val date: String? = null,

	@SerializedName("date_epoch")
	val dateEpoch: Int? = null,

	@SerializedName("day")
	val day: Day? = null
)

data class Day(

	@SerializedName("maxtemp_f")
	val maxtempF: Double? = null,

	@SerializedName("mintemp_f")
	val mintempF: Double? = null,

	@SerializedName("condition")
	val condition: ConditionResponse
)

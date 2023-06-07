package by.baranovdev.weathertest.data.mapper

import by.baranovdev.weathertest.data.api.response.ForecastResponse
import by.baranovdev.weathertest.data.model.Forecast
import by.baranovdev.weathertest.data.model.ForecastDay
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class NetworkForecastMapper: BaseNetworkMapper<ForecastResponse, Forecast> {
    override fun mapNetworkToUi(response: ForecastResponse): Forecast =
        Forecast(
            response.forecast?.forecastday?.filterNotNull()?.mapIndexed { index, it ->
                val day = when(index){
                    0 -> "Today"
                    1 -> "Tomorrow"
                    else -> {
                        val sdfDate = SimpleDateFormat("EEEE", Locale.ROOT)
                        sdfDate.format(TimeUnit.SECONDS.toMillis(it.dateEpoch?.toLong() ?: 0L))
                    }
                }
                ForecastDay(
                    date = it.date.toString(),
                    maxTemperature = it.day?.maxtempF ?: .0,
                    minTemperature = it.day?.mintempF ?: .0,
                    icon = it.day?.condition?.icon.toString(),
                    day = day
                )
            } ?: emptyList()
        )
}
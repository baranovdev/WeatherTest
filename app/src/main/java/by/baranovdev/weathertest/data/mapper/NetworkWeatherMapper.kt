package by.baranovdev.weathertest.data.mapper

import by.baranovdev.weathertest.data.api.response.WeatherResponse
import by.baranovdev.weathertest.data.model.Weather
import javax.inject.Inject

class NetworkWeatherMapper @Inject constructor(
    private val networkLocationMapper: NetworkLocationMapper,
    private val networkCurrentMapper: NetworkCurrentMapper
):BaseNetworkMapper<WeatherResponse, Weather> {
    override fun mapNetworkToUi(response: WeatherResponse): Weather =
        Weather(
            networkLocationMapper.mapNetworkToUi(response.location),
            networkCurrentMapper.mapNetworkToUi(response.current)
        )
}
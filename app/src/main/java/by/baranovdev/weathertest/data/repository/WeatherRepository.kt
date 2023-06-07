package by.baranovdev.weathertest.data.repository

import by.baranovdev.weathertest.data.api.service.WeatherService
import by.baranovdev.weathertest.data.mapper.NetworkWeatherMapper
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val networkWeatherMapper: NetworkWeatherMapper
) {

    suspend fun getWeather(city: String) = networkWeatherMapper.mapNetworkToUi(weatherService.getWeather(city = city))
}

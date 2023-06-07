package by.baranovdev.weathertest.data.repository

import by.baranovdev.weathertest.data.api.service.ForecastService
import by.baranovdev.weathertest.data.mapper.NetworkForecastMapper
import by.baranovdev.weathertest.data.model.Forecast
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val forecastService: ForecastService,
    private val networkForecastMapper: NetworkForecastMapper
){
    suspend fun getForecast(city: String): Forecast = networkForecastMapper.mapNetworkToUi(forecastService.getForecast(city = city))
}
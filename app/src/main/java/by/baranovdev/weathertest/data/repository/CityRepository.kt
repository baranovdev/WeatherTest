package by.baranovdev.weathertest.data.repository

import by.baranovdev.weathertest.data.api.service.SearchService
import by.baranovdev.weathertest.data.mapper.NetworkCityMapper
import by.baranovdev.weathertest.data.model.City
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val searchService: SearchService,
    private val networkCityMapper: NetworkCityMapper
) {
    suspend fun searchCity(query: String): List<City> =
        searchService.searchCity(query = query).map { networkCityMapper.mapNetworkToUi(it) }

}
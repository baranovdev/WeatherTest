package by.baranovdev.weathertest.data.mapper

import by.baranovdev.weathertest.data.api.response.CityResponse
import by.baranovdev.weathertest.data.model.City
import by.baranovdev.weathertest.utils.DefaultValues

class NetworkCityMapper: BaseNetworkMapper<CityResponse, City> {
    override fun mapNetworkToUi(response: CityResponse): City = City(
        response.name ?: DefaultValues.STRING_UNDEFINED,
        response.country ?: DefaultValues.STRING_UNDEFINED
    )
}
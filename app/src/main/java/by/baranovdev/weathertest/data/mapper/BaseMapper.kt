package by.baranovdev.weathertest.data.mapper

interface BaseNetworkMapper<R,U> {
    fun mapNetworkToUi(response: R):U
}
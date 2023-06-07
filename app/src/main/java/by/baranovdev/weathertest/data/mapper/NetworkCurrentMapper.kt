package by.baranovdev.weathertest.data.mapper

import by.baranovdev.weathertest.data.api.response.CurrentResponse
import by.baranovdev.weathertest.data.model.Condition
import by.baranovdev.weathertest.data.model.Current
import by.baranovdev.weathertest.utils.DefaultValues

class NetworkCurrentMapper: BaseNetworkMapper<CurrentResponse?, Current> {
    override fun mapNetworkToUi(response: CurrentResponse?): Current = Current(
        uv = response?.uv.toString(),
        condition = Condition(
            icon = "https:".plus(response?.condition?.icon ?: DefaultValues.STRING_UNDEFINED),
            text = response?.condition?.text ?: DefaultValues.STRING_UNDEFINED
        ),
        windMph = response?.windMph,
        humidity = response?.humidity ?: DefaultValues.INT_UNDEFINED,
        tempF = response?.tempF
        )
}
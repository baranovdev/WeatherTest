package by.baranovdev.weathertest.ui.fragments.weather.epoxy

import by.baranovdev.weathertest.data.model.Forecast
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController

class ForecastEpoxyController() : TypedEpoxyController<Forecast>() {

    @AutoModel
    lateinit var forecastItemModel: UiForecastItemModel

    override fun buildModels(data: Forecast?) {
        data?.forecastList?.forEachIndexed { index, forecastDay ->
            UiForecastItemModel_()
                .forecast(forecastDay)
                .id(index)
                .addTo(this)
        }
    }

}
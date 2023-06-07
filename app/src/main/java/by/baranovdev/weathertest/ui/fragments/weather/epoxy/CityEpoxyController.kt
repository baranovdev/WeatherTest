package by.baranovdev.weathertest.ui.fragments.weather.epoxy

import by.baranovdev.weathertest.ui.fragments.weather.WeatherViewModel
import com.airbnb.epoxy.*

class CityEpoxyController(
    private val viewModel: WeatherViewModel
) : TypedEpoxyController<List<String>>(), OnCityItemClickListener {

    @AutoModel
    lateinit var cityItemModel: UiCityItemModel

    override fun buildModels(data: List<String>?) {

        data?.forEachIndexed { index, s ->
            UiCityItemModel_().cityName(s).listener(this).id(index).addTo(this)
        }
    }

    override fun onCityItemClick(name: String) {
        viewModel.getWeather(name)
    }
}
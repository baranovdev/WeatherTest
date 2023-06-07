package by.baranovdev.weathertest.ui.fragments.weather

import by.baranovdev.weathertest.data.model.Forecast
import by.baranovdev.weathertest.data.model.Weather
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class WeatherState(
    val weather: Async<Weather?> = Uninitialized,
    val cities: Async<List<String>?> = Uninitialized,
    val forecast: Async<Forecast?> = Uninitialized,
    val isSearchVisible: Boolean = false,
) : MavericksState
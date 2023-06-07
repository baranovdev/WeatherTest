package by.baranovdev.weathertest.ui.fragments.weather

import by.baranovdev.weathertest.data.repository.CityRepository
import by.baranovdev.weathertest.data.repository.ForecastRepository
import by.baranovdev.weathertest.data.repository.WeatherRepository
import by.baranovdev.weathertest.di.AssistedViewModelFactory
import by.baranovdev.weathertest.di.DaggerMavericksViewModelFactory
import by.baranovdev.weathertest.utils.DefaultValues

import com.airbnb.mvrx.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class WeatherViewModel @AssistedInject constructor(
    @Assisted state: WeatherState,
    private val repository: WeatherRepository,
    private val cityRepository: CityRepository,
    private val forecastRepository: ForecastRepository
) : MavericksViewModel<WeatherState>(state) {

    init {
        getWeather(DefaultValues.DEFAULT_CITY)
        getForecast(DefaultValues.DEFAULT_CITY)
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            flow {
                emit(repository.getWeather(city))
            }.execute { copy(weather = it) }
            hideSearch()
        }
    }

    fun searchCity(query: String){
        viewModelScope.launch {
            flow {
                emit(cityRepository.searchCity(query).map { "${it.name}, ${it.country}"})
            }.execute { copy( cities = it) }
        }
    }

    fun getForecast(city: String){
        viewModelScope.launch {
            flow {
                emit(forecastRepository.getForecast(city))
            }.execute { copy( forecast = it) }
        }
    }

    fun openSearch(){
        setState { copy(isSearchVisible = true) }
    }

    fun hideSearch(){
        setState { copy(isSearchVisible = false) }
    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<WeatherViewModel, WeatherState> {
        override fun create(state: WeatherState): WeatherViewModel
    }

    companion object : MavericksViewModelFactory<WeatherViewModel, WeatherState> by daggerMavericksViewModelFactory()

}




inline fun <reified VM : MavericksViewModel<S>, S : MavericksState> daggerMavericksViewModelFactory() =
    DaggerMavericksViewModelFactory<VM, S>(VM::class.java)
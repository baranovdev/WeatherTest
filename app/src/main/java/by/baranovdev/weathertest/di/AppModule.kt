package by.baranovdev.weathertest.di

import by.baranovdev.weathertest.ui.fragments.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun weatherViewModelFactory(factory: WeatherViewModel.Factory): AssistedViewModelFactory<*, *>
}
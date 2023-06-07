package by.baranovdev.weathertest.di

import by.baranovdev.weathertest.data.mapper.*
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideNetworkWeatherMapper(networkLocationMapper: NetworkLocationMapper, networkCurrentMapper: NetworkCurrentMapper): NetworkWeatherMapper = NetworkWeatherMapper(
        networkLocationMapper,
        networkCurrentMapper
    )

    @Provides
    fun provideNetworkLocationMapper(): NetworkLocationMapper = NetworkLocationMapper()

    @Provides
    fun provideNetworkCurrentMapper(): NetworkCurrentMapper = NetworkCurrentMapper()

    @Provides
    fun provideCityMapper(): NetworkCityMapper = NetworkCityMapper()

    @Provides
    fun provideForecastMapper(): NetworkForecastMapper = NetworkForecastMapper()

}
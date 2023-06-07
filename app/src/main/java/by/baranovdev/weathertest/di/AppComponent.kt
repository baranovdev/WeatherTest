package by.baranovdev.weathertest.di

import com.airbnb.mvrx.MavericksViewModel
import dagger.Component

@Component(modules = [AppModule::class, RetrofitModule::class, MapperModule::class])
interface AppComponent {
    fun viewModelFactories(): Map<Class<out MavericksViewModel<*>>, AssistedViewModelFactory<*, *>>
}
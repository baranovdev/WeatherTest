package by.baranovdev.weathertest.application

import android.app.Application
import androidx.activity.ComponentActivity
import by.baranovdev.weathertest.di.AppComponent
import by.baranovdev.weathertest.di.DaggerAppComponent
import com.airbnb.mvrx.Mavericks

class WeatherApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        Mavericks.initialize(this)
    }
}

fun ComponentActivity.appComponent(): AppComponent {
    return (application as WeatherApplication).appComponent
}
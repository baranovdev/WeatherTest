package by.baranovdev.weathertest.ui.fragments.weather.epoxy

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import by.baranovdev.weathertest.R
import by.baranovdev.weathertest.data.model.ForecastDay
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_forecast)
open class UiForecastItemModel: EpoxyModelWithHolder<UiForecastItemModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var forecast: ForecastDay

    override fun getDefaultLayout() = R.layout.item_forecast
    override fun createNewHolder(parent: ViewParent): ViewHolder = ViewHolder()

    @SuppressLint("SetTextI18n")
    override fun bind(holder: ViewHolder) {
        holder.forecastTemperature.text = "${forecast.minTemperature.toString()} °F / ${forecast.minTemperature.toString()} °F"
        Glide.with(holder.forecastIcon).load("https:".plus(forecast.icon)).into(holder.forecastIcon)
        holder.forecastDay.text = forecast.day
    }

    class ViewHolder: EpoxyHolder(){
        lateinit var forecastTemperature: TextView
        lateinit var forecastIcon: ImageView
        lateinit var forecastDay: TextView

        override fun bindView(itemView: View) {
            forecastTemperature = itemView.findViewById(R.id.forecast_temperature)
            forecastIcon = itemView.findViewById(R.id.forecast_icon)
            forecastDay = itemView.findViewById(R.id.forecast_day)
        }

    }

}
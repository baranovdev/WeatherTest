package by.baranovdev.weathertest.ui.fragments.weather.epoxy

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewParent
import android.widget.TextView
import by.baranovdev.weathertest.R
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_city)
open class UiCityItemModel: EpoxyModelWithHolder<UiCityItemModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var cityName: String

    @EpoxyAttribute
    lateinit var listener: OnCityItemClickListener

    override fun getDefaultLayout() = R.layout.item_city
    override fun createNewHolder(parent: ViewParent): ViewHolder = ViewHolder()

    override fun bind(holder: ViewHolder) {
        holder.cityTextView.text = cityName
        holder.cityTextView.setOnClickListener { listener.onCityItemClick(name = cityName) }
    }

    class ViewHolder: EpoxyHolder(){
        lateinit var cityTextView: TextView

        override fun bindView(itemView: View) {
            cityTextView = itemView.findViewById(R.id.tv_city_name)
        }

    }

}
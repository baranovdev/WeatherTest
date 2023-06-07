package by.baranovdev.weathertest.data.mapper

import android.annotation.SuppressLint
import by.baranovdev.weathertest.data.api.response.LocationResponse
import by.baranovdev.weathertest.data.model.Location
import by.baranovdev.weathertest.utils.DefaultValues
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class NetworkLocationMapper : BaseNetworkMapper<LocationResponse?, Location> {
    @SuppressLint("SimpleDateFormat")
    override fun mapNetworkToUi(response: LocationResponse?): Location {
        @SuppressLint("ConstantLocale")
        val sdfDate = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ROOT)
        val sdfTime = SimpleDateFormat("hh:mm a", Locale.ROOT)
        return Location(
            localtime = sdfDate.format(TimeUnit.SECONDS.toMillis(response?.localtimeEpoch?.toLong() ?: 0L)) ?: DefaultValues.STRING_UNDEFINED,
            country = response?.country ?: DefaultValues.STRING_UNDEFINED,
            localtimeEpoch = response?.localtimeEpoch ?: DefaultValues.INT_UNDEFINED,
            name = response?.name ?: DefaultValues.STRING_UNDEFINED,
            lon = response?.lon.toString(),
            region = response?.region ?: DefaultValues.STRING_UNDEFINED,
            lat = response?.lat.toString(),
            tzId = response?.tzId ?: DefaultValues.STRING_UNDEFINED,
            time = sdfTime.format(TimeUnit.SECONDS.toMillis(response?.localtimeEpoch?.toLong() ?: 0L)) ?: DefaultValues.STRING_UNDEFINED
        )
    }
}



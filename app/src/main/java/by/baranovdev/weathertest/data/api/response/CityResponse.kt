package by.baranovdev.weathertest.data.api.response

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("country")
    val country: String?
)
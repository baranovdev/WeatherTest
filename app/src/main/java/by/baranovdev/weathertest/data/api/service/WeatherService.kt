package by.baranovdev.weathertest.data.api.service

import by.baranovdev.weathertest.data.api.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") key: String = "b5705378be8b4fbda49211431221006",
        @Query("q") city:String,
    ): WeatherResponse
}
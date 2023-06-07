package by.baranovdev.weathertest.data.api.service

import by.baranovdev.weathertest.data.api.response.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String = "b5705378be8b4fbda49211431221006",
        @Query("q") city:String,
        @Query("days") days: Int = 3
    ): ForecastResponse
}
package by.baranovdev.weathertest.data.api.service

import by.baranovdev.weathertest.data.api.response.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search.json")
    suspend fun searchCity(
        @Query("key") key: String = "b5705378be8b4fbda49211431221006",
        @Query("q") query:String,
    ): List<CityResponse>
}
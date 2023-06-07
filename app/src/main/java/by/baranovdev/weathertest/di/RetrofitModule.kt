package by.baranovdev.weathertest.di

import android.util.Log
import by.baranovdev.weathertest.BuildConfig
import by.baranovdev.weathertest.data.api.service.ForecastService
import by.baranovdev.weathertest.data.api.service.SearchService
import by.baranovdev.weathertest.data.api.service.WeatherService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object RetrofitModule {

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    fun provideForecastService(retrofit: Retrofit): ForecastService =
        retrofit.create(ForecastService::class.java)

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Provides
    fun providesLoggingInterceptor() = HttpLoggingInterceptor {
        Log.d("Retrofit", it)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    fun provideGson(): Gson = GsonBuilder().serializeSpecialFloatingPointValues().setLenient().create()
}
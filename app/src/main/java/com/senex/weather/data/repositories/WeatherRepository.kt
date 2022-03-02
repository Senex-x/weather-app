package com.senex.weather.data.repositories;

import com.senex.weather.BuildConfig
import com.senex.weather.common.log
import com.senex.weather.data.api.Api
import com.senex.weather.data.entities.CityInfo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

typealias Longitude = Float
typealias Latitude = Float

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val API_KEY = "ed3c7ed11f28b4d4bd980e0f9b10e213"
private const val QUERY_API_KEY = "appid"

class WeatherRepository : Api {
    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()

        val newURL = original.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .build()

        chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }

    private val okhttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        HttpLoggingInterceptor().setLevel(
                            HttpLoggingInterceptor.Level.BODY
                        )
                    )
                }
            }
            .build()
    }

    private val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    override suspend fun getWeather(city: String) = api.getWeather(city)

    override suspend fun getWeather(cityId: Int) = api.getWeather(cityId)

    override suspend fun getWeather(
        lat: Latitude, lon: Longitude,
    ) = api.getWeather(lat, lon)

    suspend fun getWeather(
        coordinates: Map<Latitude, Longitude>,
    ): List<CityInfo> {
        val list = mutableListOf<CityInfo>()

        coordinates.toString().log()

        for ((lat, lon) in coordinates) {
            list.add(getWeather(lat, lon))
        }

        return list
    }
}
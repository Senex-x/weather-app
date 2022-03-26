package com.senex.weather.data.repository;

import com.senex.weather.BuildConfig
import com.senex.weather.common.Latitude
import com.senex.weather.common.Longitude
import com.senex.weather.common.log
import com.senex.weather.data.api.WeatherApi
import com.senex.weather.data.mapper.transform
import com.senex.weather.domain.entities.CityInfo
import com.senex.weather.domain.repository.WeatherRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val API_KEY = "ed3c7ed11f28b4d4bd980e0f9b10e213"
private const val QUERY_API_KEY = "appid"

class RemoteWeatherRepository : WeatherRepository {
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

    private val unitsTypeInterceptor = Interceptor { chain ->
        chain.run {
            val updatedRequestUrl = request().url.newBuilder()
                .addQueryParameter("units", "metric")
                .build()

            proceed(
                request().newBuilder().url(updatedRequestUrl).build()
            )
        }
    }

    private val okhttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(unitsTypeInterceptor)
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

    private val weatherApi: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    override suspend fun getWeather(city: String) = weatherApi.getWeather(city).transform()

    override suspend fun getWeather(cityId: Int) = weatherApi.getWeather(cityId).transform()

    override suspend fun getCityInfo(
        lat: Latitude, lon: Longitude,
    ) = weatherApi.getWeather(lat, lon).transform()

    override suspend fun getCityInfo(
        coordinates: Map<Latitude, Longitude>,
    ): List<CityInfo> {
        val list = mutableListOf<CityInfo>()

        coordinates.toString().log()

        for ((lat, lon) in coordinates) {
            val city = getCityInfo(lat, lon)
            if (city.name.isNotBlank()) list.add(city)
        }

        return list
    }
}

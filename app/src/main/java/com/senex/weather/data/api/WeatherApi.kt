package com.senex.weather.data.api

import com.senex.weather.data.entities.CityInfoEntity
import com.senex.weather.data.entities.WeatherEntity
import com.senex.weather.domain.entities.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(@Query("q") city: String): WeatherEntity

    @GET("weather")
    suspend fun getWeather(@Query("id") cityId: Int): WeatherEntity

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
    ): CityInfoEntity
}

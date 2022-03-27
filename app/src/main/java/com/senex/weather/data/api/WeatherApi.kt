package com.senex.weather.data.api

import com.senex.weather.data.entity.CityInfoEntity
import com.senex.weather.data.entity.WeatherEntity
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

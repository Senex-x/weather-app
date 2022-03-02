package com.senex.weather.data.api

import com.senex.weather.data.entities.CityInfo
import com.senex.weather.data.entities.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather?units=metric&lang=EN")
    suspend fun getWeather(@Query("q") city: String): Weather

    @GET("weather")
    suspend fun getWeather(@Query("id") cityId: Int): Weather

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
    ): CityInfo
}

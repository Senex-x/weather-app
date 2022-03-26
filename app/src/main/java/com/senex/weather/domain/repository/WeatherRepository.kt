package com.senex.weather.domain.repository

import com.senex.weather.common.Latitude
import com.senex.weather.common.Longitude
import com.senex.weather.domain.entities.CityInfo
import com.senex.weather.domain.entities.Weather

interface WeatherRepository {
    suspend fun getWeather(
        city: String,
    ): Weather

    suspend fun getWeather(
        cityId: Int,
    ): Weather

    suspend fun getCityInfo(
        lat: Latitude, lon: Longitude,
    ): CityInfo

    suspend fun getCityInfo(
        coordinates: Map<Latitude, Longitude>,
    ): List<CityInfo>
}

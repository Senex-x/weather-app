package com.senex.weather.domain.repository

import com.senex.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun get(
        city: String,
    ): Weather

    suspend fun get(
        cityId: Int,
    ): Weather
}

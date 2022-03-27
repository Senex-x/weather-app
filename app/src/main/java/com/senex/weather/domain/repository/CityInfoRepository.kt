package com.senex.weather.domain.repository

import com.senex.weather.domain.util.Latitude
import com.senex.weather.domain.util.Longitude
import com.senex.weather.domain.model.CityInfo

interface CityInfoRepository {
    suspend fun get(
        lat: Latitude, lon: Longitude,
    ): CityInfo

    suspend fun get(
        coordinates: Map<Latitude, Longitude>,
    ): List<CityInfo>
}
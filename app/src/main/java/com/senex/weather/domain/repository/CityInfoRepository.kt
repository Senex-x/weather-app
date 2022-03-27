package com.senex.weather.domain.repository

import com.senex.weather.common.Latitude
import com.senex.weather.common.Longitude
import com.senex.weather.domain.model.CityInfo

interface CityInfoRepository {
    suspend fun get(
        lat: Latitude, lon: Longitude,
    ): CityInfo

    suspend fun get(
        coordinates: Map<Latitude, Longitude>,
    ): List<CityInfo>
}
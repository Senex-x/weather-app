package com.senex.weather.data.repository

import com.senex.weather.domain.util.Latitude
import com.senex.weather.domain.util.Longitude
import com.senex.weather.presentation.common.log
import com.senex.weather.data.mapper.transform
import com.senex.weather.domain.model.CityInfo
import com.senex.weather.domain.repository.CityInfoRepository
import javax.inject.Inject

class CityInfoRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
) : CityInfoRepository {

    override suspend fun get(
        lat: Latitude, lon: Longitude,
    ) = weatherService.getWeather(lat, lon).transform()

    override suspend fun get(
        coordinates: Map<Latitude, Longitude>,
    ): List<CityInfo> {
        val list = mutableListOf<CityInfo>()

        coordinates.toString().log()

        for ((lat, lon) in coordinates) {
            val city = get(lat, lon)
            if (city.name.isNotBlank()) list.add(city)
        }

        return list
    }
}
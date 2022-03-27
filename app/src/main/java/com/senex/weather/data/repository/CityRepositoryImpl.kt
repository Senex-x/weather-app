package com.senex.weather.data.repository

import com.senex.weather.common.Latitude
import com.senex.weather.common.Longitude
import com.senex.weather.common.log
import com.senex.weather.data.api.WeatherApiProvider
import com.senex.weather.data.mapper.transform
import com.senex.weather.domain.model.CityInfo
import com.senex.weather.domain.repository.CityInfoRepository

class CityRepositoryImpl: CityInfoRepository {
    override suspend fun get(
        lat: Latitude, lon: Longitude,
    ) = WeatherApiProvider.weatherApi.getWeather(lat, lon).transform()

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
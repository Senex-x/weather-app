package com.senex.weather.domain.usecase

import com.senex.weather.domain.util.Latitude
import com.senex.weather.domain.util.Longitude
import com.senex.weather.domain.repository.CityInfoRepository

class GetCityInfo(
    private val weatherRepository: CityInfoRepository
) {
    suspend operator fun invoke(
        lat: Latitude,
        lon: Longitude,
    ) = weatherRepository.get(lat, lon)
}
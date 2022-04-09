package com.senex.weather.domain.usecase

import com.senex.weather.domain.util.Latitude
import com.senex.weather.domain.util.Longitude
import com.senex.weather.domain.repository.CityInfoRepository

class GetCityInfoList(
    private val weatherRepository: CityInfoRepository
) {
    suspend operator fun invoke(
        coordinates: Map<Latitude, Longitude>,
    ) = weatherRepository.get(coordinates)
}
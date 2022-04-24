package com.senex.weather.domain.usecase

import com.senex.weather.domain.util.Latitude
import com.senex.weather.domain.util.Longitude
import com.senex.weather.domain.repository.CityInfoRepository
import javax.inject.Inject

class GetCityInfoList @Inject constructor(
    private val weatherRepository: CityInfoRepository
) {
    suspend operator fun invoke(
        coordinates: Map<Latitude, Longitude>,
    ) = weatherRepository.get(coordinates)
}
package com.senex.weather.domain.usecase

import com.senex.weather.common.Latitude
import com.senex.weather.common.Longitude
import com.senex.weather.domain.repository.CityInfoRepository
import com.senex.weather.domain.repository.WeatherRepository

class GetCityInfoList(
    private val weatherRepository: CityInfoRepository
) {
    suspend operator fun invoke(
        coordinates: Map<Latitude, Longitude>,
    ) = weatherRepository.get(coordinates)
}
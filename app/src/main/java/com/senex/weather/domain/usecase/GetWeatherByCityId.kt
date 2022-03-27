package com.senex.weather.domain.usecase

import com.senex.weather.domain.repository.WeatherRepository

class GetWeatherByCityId(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        cityId: Int
    ) = weatherRepository.get(cityId)
}
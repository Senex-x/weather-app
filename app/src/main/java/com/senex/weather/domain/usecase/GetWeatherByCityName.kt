package com.senex.weather.domain.usecase

import com.senex.weather.domain.repository.WeatherRepository

class GetWeatherByCityName(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        cityName: String
    ) = weatherRepository.get(cityName)
}
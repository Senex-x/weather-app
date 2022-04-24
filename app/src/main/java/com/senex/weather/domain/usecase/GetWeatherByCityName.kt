package com.senex.weather.domain.usecase

import com.senex.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByCityName @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        cityName: String
    ) = weatherRepository.get(cityName)
}
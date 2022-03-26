package com.senex.weather.domain.usecases

import com.senex.weather.domain.repository.WeatherRepository

class GetWeatherByCityId(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        cityId: Int
    ) = weatherRepository.getWeather(cityId)
}
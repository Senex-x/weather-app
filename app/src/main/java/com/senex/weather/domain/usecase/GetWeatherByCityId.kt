package com.senex.weather.domain.usecase

import com.senex.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByCityId @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        cityId: Int
    ) = weatherRepository.get(cityId)
}
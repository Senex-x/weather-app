package com.senex.weather.domain.usecases

import com.senex.weather.common.Latitude
import com.senex.weather.common.Longitude
import com.senex.weather.data.repository.WeatherRepository

class GetCityInfo {
    private val weatherRepository = WeatherRepository()

    suspend operator fun invoke(
        lat: Latitude,
        lon: Longitude,
    ) = weatherRepository.getWeather(lat, lon)
}
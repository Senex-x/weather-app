package com.senex.weather.domain.usecases

import com.senex.weather.common.Latitude
import com.senex.weather.common.Longitude
import com.senex.weather.data.repository.WeatherRepository

class GetCityInfoList {
    private val weatherRepository = WeatherRepository()

    suspend operator fun invoke(
        coordinates: Map<Latitude, Longitude>,
    ) = weatherRepository.getWeather(coordinates)
}
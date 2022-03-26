package com.senex.weather.domain.usecases

import com.senex.weather.common.Latitude
import com.senex.weather.common.Longitude
import com.senex.weather.data.repository.RemoteWeatherRepository
import com.senex.weather.domain.repository.WeatherRepository

class GetWeatherByCityName(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        cityName: String
    ) = weatherRepository.getWeather(cityName)
}
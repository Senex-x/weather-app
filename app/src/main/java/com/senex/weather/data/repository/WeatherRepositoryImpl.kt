package com.senex.weather.data.repository;

import com.senex.weather.data.api.WeatherService
import com.senex.weather.data.mapper.transform
import com.senex.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
) : WeatherRepository {

    override suspend fun get(city: String) =
        weatherService.getWeather(city).transform()

    override suspend fun get(cityId: Int) =
        weatherService.getWeather(cityId).transform()
}

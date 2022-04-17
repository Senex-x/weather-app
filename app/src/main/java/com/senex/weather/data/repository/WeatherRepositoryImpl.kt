package com.senex.weather.data.repository;

import com.senex.weather.data.api.WeatherApiProvider
import com.senex.weather.data.mapper.transform
import com.senex.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor() : WeatherRepository {
    private val weatherApi = WeatherApiProvider.weatherApi

    override suspend fun get(city: String) = weatherApi.getWeather(city).transform()

    override suspend fun get(cityId: Int) = weatherApi.getWeather(cityId).transform()
}

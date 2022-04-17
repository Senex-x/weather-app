package com.senex.weather.presentation.di

import com.senex.weather.data.repository.CityInfoRepositoryImpl
import com.senex.weather.data.repository.WeatherRepositoryImpl
import com.senex.weather.domain.repository.CityInfoRepository
import com.senex.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCityInfoRepository(
        repository: CityInfoRepositoryImpl,
    ): CityInfoRepository

    @Singleton
    @Binds
    abstract fun bindWeatherRepository(
        repository: WeatherRepositoryImpl,
    ): WeatherRepository
}
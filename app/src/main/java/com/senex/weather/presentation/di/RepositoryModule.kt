package com.senex.weather.presentation.di

import com.senex.weather.data.repository.CityInfoRepositoryImpl
import com.senex.weather.data.repository.WeatherRepositoryImpl
import com.senex.weather.domain.repository.CityInfoRepository
import com.senex.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCityInfoRepository(
        repository: CityInfoRepositoryImpl,
    ): CityInfoRepository

    @Binds
    abstract fun bindWeatherRepository(
        repository: WeatherRepositoryImpl,
    ): WeatherRepository
}
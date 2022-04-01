package com.senex.weather.presentation.di

import com.senex.weather.data.repository.CityInfoRepositoryImpl
import com.senex.weather.data.repository.WeatherRepositoryImpl
import com.senex.weather.domain.repository.CityInfoRepository
import com.senex.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [RepositoryCreatorModule::class])
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindCityInfoRepository(
        repository: CityInfoRepositoryImpl,
    ): CityInfoRepository

    @Singleton
    @Binds
    fun bindWeatherRepository(
        repository: WeatherRepositoryImpl,
    ): WeatherRepository
}
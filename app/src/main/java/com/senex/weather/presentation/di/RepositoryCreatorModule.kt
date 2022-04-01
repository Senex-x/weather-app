package com.senex.weather.presentation.di

import com.senex.weather.data.repository.CityInfoRepositoryImpl
import com.senex.weather.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryCreatorModule {

    @Singleton
    @Provides
    fun provideCityInfoRepositoryImpl() = CityInfoRepositoryImpl()

    @Singleton
    @Provides
    fun provideWeatherRepositoryImpl() = WeatherRepositoryImpl()
}
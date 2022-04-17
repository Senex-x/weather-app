package com.senex.weather.presentation.di

import com.senex.weather.domain.repository.CityInfoRepository
import com.senex.weather.domain.repository.WeatherRepository
import com.senex.weather.domain.usecase.GetCityInfo
import com.senex.weather.domain.usecase.GetCityInfoList
import com.senex.weather.domain.usecase.GetWeatherByCityId
import com.senex.weather.domain.usecase.GetWeatherByCityName
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCityInfoUseCase(
        cityInfoRepository: CityInfoRepository
    ) = GetCityInfo(cityInfoRepository)

    @Singleton
    @Provides
    fun provideGetCityInfoListUseCase(
        cityInfoRepository: CityInfoRepository
    ) = GetCityInfoList(cityInfoRepository)

    @Singleton
    @Provides
    fun provideGetWeatherByCityIdUseCase(
        weatherRepository: WeatherRepository
    ) = GetWeatherByCityId(weatherRepository)

    @Singleton
    @Provides
    fun provideGetWeatherByCityNameUseCase(
        weatherRepository: WeatherRepository
    ) = GetWeatherByCityName(weatherRepository)
}
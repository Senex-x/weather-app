package com.senex.weather.presentation

import com.senex.weather.domain.repository.WeatherRepository
import com.senex.weather.domain.usecase.GetCityInfo
import com.senex.weather.presentation.di.DaggerAppComponent
import dagger.android.DaggerApplication
import javax.inject.Inject

class WeatherApp : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()

    @Inject
    lateinit var repo: WeatherRepository

    @Inject
    lateinit var use: GetCityInfo
}
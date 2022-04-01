package com.senex.weather.presentation

import com.senex.weather.presentation.di.DaggerAppComponent
import dagger.android.DaggerApplication

class WeatherApp : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()
}
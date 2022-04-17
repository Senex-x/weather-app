package com.senex.weather.presentation.di

import com.senex.weather.presentation.ui.cities.CityListFragment
import com.senex.weather.presentation.ui.weather.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeCityListFragment(): CityListFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherFragment(): WeatherFragment
}
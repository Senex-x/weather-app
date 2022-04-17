package com.senex.weather.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senex.weather.presentation.ui.cities.CityListViewModel
import com.senex.weather.presentation.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CityListViewModel::class)
    abstract fun bindGroupsViewModel(
        viewModel: CityListViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindScheduleViewModel(
        viewModel: WeatherViewModel
    ): ViewModel
}
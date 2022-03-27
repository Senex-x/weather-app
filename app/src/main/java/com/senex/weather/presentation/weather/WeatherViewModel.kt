package com.senex.weather.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senex.weather.data.repository.WeatherRepositoryImpl
import com.senex.weather.domain.model.Weather
import com.senex.weather.domain.repository.WeatherRepository
import com.senex.weather.domain.usecase.GetWeatherByCityId
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    private val repository: WeatherRepository = WeatherRepositoryImpl()
    private val getWeatherByCityId = GetWeatherByCityId(repository)

    private val weather: MutableLiveData<Weather> = MutableLiveData()

    fun getWeather(cityId: Int): LiveData<Weather> {
        viewModelScope.launch {
            weather.postValue(getWeatherFromRepository(cityId))
        }
        return weather
    }

    private suspend fun getWeatherFromRepository(cityId: Int) = getWeatherByCityId(cityId)
}
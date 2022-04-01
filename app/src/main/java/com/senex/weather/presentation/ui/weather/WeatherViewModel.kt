package com.senex.weather.presentation.ui.weather

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

    private val _weather: MutableLiveData<Weather> = MutableLiveData()
    val weather: LiveData<Weather>
        get() = _weather

    fun getWeather(cityId: Int): LiveData<Weather> {
        viewModelScope.launch {
            _weather.postValue(getWeatherFromRepository(cityId))
        }
        return weather
    }

    private suspend fun getWeatherFromRepository(cityId: Int) = getWeatherByCityId(cityId)
}
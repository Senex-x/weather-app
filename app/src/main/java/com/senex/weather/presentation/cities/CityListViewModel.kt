package com.senex.weather.presentation.cities

import android.location.Location
import androidx.lifecycle.ViewModel
import com.senex.weather.domain.util.Latitude
import com.senex.weather.domain.util.Longitude
import com.senex.weather.data.repository.CityRepositoryImpl
import com.senex.weather.data.repository.WeatherRepositoryImpl
import com.senex.weather.domain.repository.CityInfoRepository
import com.senex.weather.domain.repository.WeatherRepository
import com.senex.weather.domain.usecase.GetCityInfoList
import com.senex.weather.domain.usecase.GetWeatherByCityName
import com.senex.weather.presentation.common.log
import kotlin.random.Random

const val DEFAULT_COORD = 49F
const val SCATTER_VALUE = 20

class CityListViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    private val cityInfoRepository: CityInfoRepository = CityRepositoryImpl()

    private val getWeatherByCityName = GetWeatherByCityName(weatherRepository)
    private val getCityInfoList = GetCityInfoList(cityInfoRepository)

    suspend fun getCityId(
        cityName: String,
    ) = runCatching {
        getWeatherByCityName(cityName).id
    }.getOrNull()

    suspend fun getCityInfoList(location: Location?) = getCityInfoList(
        getMap(
            20,
            location?.latitude?.toFloat() ?: DEFAULT_COORD,
            location?.longitude?.toFloat() ?: DEFAULT_COORD,
        )
    )

    @Suppress("ReplacePutWithAssignment")
    private fun getMap(
        count: Int,
        baseLat: Latitude,
        baseLon: Longitude,
    ): Map<Latitude, Longitude> {
        val map = mutableMapOf<Latitude, Longitude>()

        for (i in 1..count) {
            map.put(
                baseLat + Random.nextInt(-SCATTER_VALUE, SCATTER_VALUE),
                baseLon + Random.nextInt(-SCATTER_VALUE, SCATTER_VALUE),
            )
        }

        return map
    }
}
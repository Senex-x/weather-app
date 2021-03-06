package com.senex.weather.presentation.ui.cities

import android.location.Location
import androidx.lifecycle.ViewModel
import com.senex.weather.domain.usecase.GetCityInfoList
import com.senex.weather.domain.usecase.GetWeatherByCityName
import com.senex.weather.domain.util.Latitude
import com.senex.weather.domain.util.Longitude
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val getWeatherByCityName: GetWeatherByCityName,
    private val getCityInfoList: GetCityInfoList,
) : ViewModel() {

    suspend fun getCityId(
        cityName: String,
    ) = runCatching {
        getWeatherByCityName(cityName).id
    }.getOrNull()

    suspend fun getCityInfoList(location: Location?) = getCityInfoList(
        getMap(
            CITY_AMOUNT,
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

    companion object {
        private const val DEFAULT_COORD = 49F
        private const val SCATTER_VALUE = 20
        private const val CITY_AMOUNT = 5
    }
}
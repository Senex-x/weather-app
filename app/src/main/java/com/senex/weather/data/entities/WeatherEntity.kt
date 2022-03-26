package com.senex.weather.data.entities

import com.senex.weather.domain.entities.Clouds
import com.senex.weather.domain.entities.Coord
import com.senex.weather.domain.entities.Main
import com.senex.weather.domain.entities.Sys
import com.senex.weather.domain.entities.WeatherX
import com.senex.weather.domain.entities.Wind

data class WeatherEntity(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind,
)


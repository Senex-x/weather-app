package com.senex.weather.data.entities

data class WeatherEntity(
    val base: String,
    val clouds: CloudsEntity,
    val cod: Int,
    val coord: CoordEntity,
    val dt: Int,
    val id: Int,
    val main: MainEntity,
    val name: String,
    val sys: SysEntity,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherXEntity>,
    val wind: WindEntity,
)


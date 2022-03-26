package com.senex.weather.data.entities

import com.senex.weather.domain.entities.Main

data class CityInfoEntity(
    val name: String,
    val main: Main,
)
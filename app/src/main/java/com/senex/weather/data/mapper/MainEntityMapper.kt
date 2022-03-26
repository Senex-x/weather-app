package com.senex.weather.data.mapper

import com.senex.weather.data.entities.MainEntity
import com.senex.weather.domain.entities.Main

fun MainEntity.transform() = Main(
    feels_like,
    humidity,
    pressure,
    temp,
    temp_max,
    temp_min,
)

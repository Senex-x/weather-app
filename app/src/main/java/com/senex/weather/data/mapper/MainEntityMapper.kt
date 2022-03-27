package com.senex.weather.data.mapper

import com.senex.weather.data.entity.MainEntity
import com.senex.weather.domain.model.Main

fun MainEntity.transform() = Main(
    feels_like,
    humidity,
    pressure,
    temp,
    temp_max,
    temp_min,
)

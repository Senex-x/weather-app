package com.senex.weather.data.mapper

import com.senex.weather.data.entities.WindEntity
import com.senex.weather.domain.entities.Wind

fun WindEntity.transform() = Wind(
    deg,
    gust,
    speed,
)

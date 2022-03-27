package com.senex.weather.data.mapper

import com.senex.weather.data.entities.WindEntity
import com.senex.weather.domain.model.Wind

fun WindEntity.transform() = Wind(
    deg,
    gust,
    speed,
)

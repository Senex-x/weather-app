package com.senex.weather.data.mapper

import com.senex.weather.data.entities.WeatherXEntity
import com.senex.weather.domain.entities.WeatherX

fun WeatherXEntity.transform() = WeatherX(
    description,
    icon,
    id,
    main,
)

package com.senex.weather.data.mapper

import com.senex.weather.data.entity.WeatherXEntity
import com.senex.weather.domain.model.WeatherX

fun WeatherXEntity.transform() = WeatherX(
    description,
    icon,
    id,
    main,
)

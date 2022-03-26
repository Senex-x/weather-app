package com.senex.weather.data.mapper

import com.senex.weather.data.entities.WeatherEntity
import com.senex.weather.domain.entities.Weather

fun WeatherEntity.transform() = Weather(
    base,
    clouds,
    cod,
    coord,
    dt,
    id,
    main,
    name,
    sys,
    timezone,
    visibility,
    weather,
    wind,
)

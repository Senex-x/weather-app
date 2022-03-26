package com.senex.weather.data.mapper

import com.senex.weather.data.entities.WeatherEntity
import com.senex.weather.domain.entities.Weather

fun WeatherEntity.transform() = Weather(
    base,
    clouds.transform(),
    cod,
    coord.transform(),
    dt,
    id,
    main.transform(),
    name,
    sys.transform(),
    timezone,
    visibility,
    weather.map { it.transform() },
    wind.transform(),
)

package com.senex.weather.data.mapper

import com.senex.weather.data.entity.WeatherEntity
import com.senex.weather.domain.model.Weather

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

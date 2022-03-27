package com.senex.weather.data.mapper

import com.senex.weather.data.entities.CoordEntity
import com.senex.weather.domain.model.Coord

fun CoordEntity.transform() = Coord(
    lat,
    lon,
)

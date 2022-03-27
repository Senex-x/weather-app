package com.senex.weather.data.mapper

import com.senex.weather.data.entity.CoordEntity
import com.senex.weather.domain.model.Coord

fun CoordEntity.transform() = Coord(
    lat,
    lon,
)

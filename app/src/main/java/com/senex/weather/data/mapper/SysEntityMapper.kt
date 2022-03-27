package com.senex.weather.data.mapper

import com.senex.weather.data.entities.SysEntity
import com.senex.weather.domain.model.Sys

fun SysEntity.transform() = Sys(
    country,
    id,
    sunrise,
    sunset,
    type,
)

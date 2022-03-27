package com.senex.weather.data.mapper

import com.senex.weather.data.entities.CloudsEntity
import com.senex.weather.domain.model.Clouds

fun CloudsEntity.transform() = Clouds(
    all,
)

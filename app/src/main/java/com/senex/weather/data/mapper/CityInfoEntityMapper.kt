package com.senex.weather.data.mapper

import com.senex.weather.data.entities.CityInfoEntity
import com.senex.weather.domain.entities.CityInfo

fun CityInfoEntity.transform() = CityInfo(
    name,
    main.transform(),
)

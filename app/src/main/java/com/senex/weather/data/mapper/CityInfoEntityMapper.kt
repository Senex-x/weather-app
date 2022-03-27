package com.senex.weather.data.mapper

import com.senex.weather.data.entity.CityInfoEntity
import com.senex.weather.domain.model.CityInfo

fun CityInfoEntity.transform() = CityInfo(
    name,
    main.transform(),
)

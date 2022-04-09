package com.senex.weather.presentation.common

import androidx.annotation.DrawableRes
import com.senex.weather.R

enum class WeatherState(
    val desc: List<String>,
    @DrawableRes val background: Int,
) {
    SUN(listOf("Clear"), R.drawable.bg_sun_weather),
    FEW_CLOUDS(listOf("CloudsEntity"), R.drawable.bg_few_clouds_weather),
    FOG(listOf("Mist", "Smoke", "Haze", "Dust", "Fog", "Sand", "Dust", "Ash", "Squall", "Tornado"), R.drawable.bg_fog_weather),
    RAIN(listOf("Drizzle", "Rain"), R.drawable.bg_rain_weather),
    STORM(listOf("Thunderstorm"), R.drawable.bg_storm_weather),
    SNOW(listOf("Snow"), R.drawable.bg_snow_weather);

    companion object {
        fun get(state: String) = values().find {
            it.desc.contains(state)
        } ?: FEW_CLOUDS
    }
}
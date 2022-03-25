package com.senex.weather.common

import androidx.annotation.ColorRes
import com.senex.weather.R

enum class TemperatureLevel(
    val maxTemp: Int,
    val minTemp: Int,
    @ColorRes val color: Int,
) {
    LOWER(-20, Int.MIN_VALUE, R.color.lower_temperature),
    LOW(-5, -19, R.color.low_temperature),
    MEDIUM(4, -4, R.color.medium_temperature),
    HIGH(19, 5, R.color.high_temperature),
    HIGHER(Int.MAX_VALUE, 20, R.color.higher_temperature);

    companion object {
        fun get(temp: Int) = values().first {
            temp in it.minTemp..it.maxTemp
        }
    }
}
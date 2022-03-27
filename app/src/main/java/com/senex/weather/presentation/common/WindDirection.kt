package com.senex.weather.presentation.common

enum class WindDirection(
    val minAng: Int,
    val maxAng: Int,
) {
    NORTH(0, 0),
    NORTHEAST(23, 67),
    EAST(68, 112),
    SOUTHEAST(113, 157),
    SOUTH(158, 202),
    SOUTHWEST(203, 247),
    WEST(248, 292),
    NORTHWEST(293, 337);

    companion object {
        fun get(angle: Int) = values().find {
            angle in it.minAng..it.maxAng
        } ?: NORTH
    }
}
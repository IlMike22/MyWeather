package com.mindmarket.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDataDto(
    val time: List<String>,
    @field:Json(name = "temparature_2m")
    val temparatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name = "relativehumidity_2m")
    val humidities: List<Double>
)

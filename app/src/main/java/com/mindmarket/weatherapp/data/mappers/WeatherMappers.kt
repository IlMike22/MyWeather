package com.mindmarket.weatherapp.data.mappers

import com.mindmarket.weatherapp.data.remote.WeatherDataDto
import com.mindmarket.weatherapp.data.remote.WeatherDto
import com.mindmarket.weatherapp.domain.weather.WeatherData
import com.mindmarket.weatherapp.domain.weather.WeatherInfo
import com.mindmarket.weatherapp.domain.weather.WeatherType.Companion.fromWMO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.toWeatherMap(): Map<Int, List<WeatherData> {
    return time.mapIndexed { index, time ->
        val temparature = temparatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temparatureCelsius = temparature,
            pressure = pressure,
            windSpeed = windSpeed,
            weatherType = fromWMO(weatherCode),
            humidity = humidity
        )
    }.groupBy {
        it.weatherType
    }
}

fun WeatherDto.toWeather(): WeatherInfo {
}
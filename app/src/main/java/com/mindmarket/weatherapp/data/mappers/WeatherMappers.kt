package com.mindmarket.weatherapp.data.mappers

import com.mindmarket.weatherapp.data.remote.WeatherDataDto
import com.mindmarket.weatherapp.data.remote.WeatherDto
import com.mindmarket.weatherapp.domain.weather.WeatherData
import com.mindmarket.weatherapp.domain.weather.WeatherInfo
import com.mindmarket.weatherapp.domain.weather.WeatherType.Companion.fromWMO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temparatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temparatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                weatherType = fromWMO(weatherCode),
                humidity = humidity
            )
        )
    }.groupBy { indexedWeatherData ->
        indexedWeatherData.index / 24
    }.mapValues {
        it.value.map { weatherData ->
            weatherData.data
        }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = this.weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.hour < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}
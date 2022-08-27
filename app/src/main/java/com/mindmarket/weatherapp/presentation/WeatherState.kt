package com.mindmarket.weatherapp.presentation

import com.mindmarket.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val loading: Boolean = false,
    val error: String? = null
)

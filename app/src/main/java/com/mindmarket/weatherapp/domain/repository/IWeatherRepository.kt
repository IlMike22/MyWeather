package com.mindmarket.weatherapp.domain.repository

import com.mindmarket.weatherapp.domain.util.Resource
import com.mindmarket.weatherapp.domain.weather.WeatherInfo

interface IWeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}
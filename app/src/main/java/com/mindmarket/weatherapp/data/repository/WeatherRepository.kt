package com.mindmarket.weatherapp.data.repository

import com.mindmarket.weatherapp.data.mappers.toWeatherInfo
import com.mindmarket.weatherapp.data.remote.IWeatherApi
import com.mindmarket.weatherapp.domain.repository.IWeatherRepository
import com.mindmarket.weatherapp.domain.util.Resource
import com.mindmarket.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: IWeatherApi
) : IWeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(api.getWeatherData(lat, long).toWeatherInfo())
        } catch (exception: Throwable) {
            exception.printStackTrace()
            Resource.Error(exception.message ?: "unknown api data error")
        }
    }
}
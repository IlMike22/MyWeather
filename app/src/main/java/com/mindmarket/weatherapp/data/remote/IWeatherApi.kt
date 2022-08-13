package com.mindmarket.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApi {
    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ):WeatherDto
}
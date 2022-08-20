package com.mindmarket.weatherapp.di

import com.mindmarket.weatherapp.data.remote.IWeatherApi
import com.mindmarket.weatherapp.data.repository.WeatherRepository
import com.mindmarket.weatherapp.domain.repository.IWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepository): IWeatherRepository
}
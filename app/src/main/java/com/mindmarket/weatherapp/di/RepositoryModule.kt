package com.mindmarket.weatherapp.di

import com.mindmarket.weatherapp.data.remote.IWeatherApi
import com.mindmarket.weatherapp.data.repository.WeatherRepository
import com.mindmarket.weatherapp.domain.repository.IWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepository): IWeatherRepository
}
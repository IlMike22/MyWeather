package com.mindmarket.weatherapp.di

import com.mindmarket.weatherapp.data.location.DefaultLocationTracker
import com.mindmarket.weatherapp.domain.location.ILocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTracker): ILocationTracker
}
package com.mindmarket.weatherapp.domain.location

import android.location.Location

interface ILocationTracker {
    suspend fun getLocation():Location?
}
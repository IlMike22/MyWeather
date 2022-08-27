package com.mindmarket.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindmarket.weatherapp.data.repository.WeatherRepository
import com.mindmarket.weatherapp.domain.location.ILocationTracker
import com.mindmarket.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: ILocationTracker
) : ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                loading = true,
                error = null
            )

            val location = locationTracker.getLocation()
            location?.apply {
                when (val weatherResult = repository.getWeatherData(latitude, longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = weatherResult.data,
                            loading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            error = weatherResult.message,
                            loading = false
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    weatherInfo = null,
                    error = "Cannot get location information.",
                    loading = false
                )
            }
        }
    }

}
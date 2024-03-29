package com.example.jetweatherforecast.screens.main

import androidx.lifecycle.ViewModel
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather
import com.example.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    suspend fun getWeather(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(city = city)
    }
}
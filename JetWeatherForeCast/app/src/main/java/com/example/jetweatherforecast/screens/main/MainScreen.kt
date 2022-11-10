package com.example.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    ShowData(mainViewModel)
}

@Composable
fun ShowData(mainViewModel: MainViewModel) {
    val weather = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
            value = mainViewModel.getWeather("Seattle")
        }.value

    if (weather.loading == true) {
        CircularProgressIndicator()
    } else if (weather.data != null) {
        Text(text = "Weather city: ${weather.data!!.city.toString()}")
    } else {
        Log.d("ERROR", weather.e.toString())
    }
}
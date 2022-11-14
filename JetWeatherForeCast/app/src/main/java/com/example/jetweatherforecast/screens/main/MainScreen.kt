package com.example.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather
import com.example.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    val weather = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getWeather("Seattle")
    }.value

    if (weather.loading == true) {
        CircularProgressIndicator()
    } else if (weather.data != null) {
        MainScaffold(weather.data, navController)
    } else {
        Log.d("ERROR", weather.e.toString())
    }

}

@Composable
fun MainScaffold(data: Weather?, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "${data?.city?.name}, ${data?.city?.country}",
            navController = navController,
            elevation = 5.dp,
        ) {
            Log.d("TAG", "MainScaffold: button clicked")
        }
    }) {
        MainContent(data)
    }
}

@Composable
fun MainContent(data: Weather?) {
    Text(text = data?.city?.name ?: "null")
}

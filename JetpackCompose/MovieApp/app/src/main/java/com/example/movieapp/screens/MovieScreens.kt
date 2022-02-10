package com.example.movieapp.screens

import java.lang.IllegalArgumentException

enum class MovieScreens {
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String?): MovieScreens =
            when (route?.substringAfter(delimiter = "/")) {
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}
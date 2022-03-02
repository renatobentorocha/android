package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Magenta,
                elevation = 5.dp
            ) {
                Text(text = "Movies")
            }
        }
    ) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<Movie> = getMovies()) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        LazyColumn {
            items(items = movies) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(
                        route = MovieScreens.DetailsScreen.name + "/$movie"
                    )
                }
            }
        }
    }
}
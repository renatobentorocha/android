package com.example.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieapp.model.getMovies
import com.example.movieapp.screens.home.MainContent

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().first { movie -> movie.id == movieId }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray,
                elevation = 5.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                    Text(
                        text = "Movies"
                    )
                    Box() {
                        
                    }
                }
            }
        }
    ) {
        Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LazyRow {
                        items(movie.images) { url ->
                            Card(
                                modifier = Modifier
                                    .width(LocalConfiguration.current.screenWidthDp.dp),
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = url),
                                    contentDescription = "Movie Poster",
                                    contentScale = ContentScale.Crop
                                )
                            }

                        }
                    }

                    Text(
                        modifier = Modifier.padding(15.dp),
                        text = movie.plot,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
    }

}
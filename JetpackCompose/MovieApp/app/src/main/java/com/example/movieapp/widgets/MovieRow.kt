package com.example.movieapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onClick: (String) -> Unit = {} ) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                //Image(imageVector = Icons.Default.AccountBox, contentDescription = "Image")
                Image(
                    painter = rememberImagePainter(
                        movie.images[0],
                        builder = {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Movie poster"
                    //modifier = Modifier.size(128.dp)
                )
            }
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption
                )
            }
        }

    }
}
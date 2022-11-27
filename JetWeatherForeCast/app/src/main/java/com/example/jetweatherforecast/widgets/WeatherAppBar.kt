package com.example.jetweatherforecast.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        title = { Title(text = title)},
        actions = { Actions(isMainScreen, onButtonSearchClick = onAddActionClicked) },
        navigationIcon = { NavigationAction(icon, onButtonClicked) },
        backgroundColor = Color.Transparent, 
        elevation = elevation)
}

@Composable
fun NavigationAction(icon: ImageVector?, onButtonClicked: () -> Unit) {
    if(icon != null) {
        Icon(
            imageVector = icon,
            contentDescription = "Navigation icon",
            tint = MaterialTheme.colors.onSecondary,
            modifier = Modifier.clickable {
                onButtonClicked.invoke()
            }
        )
    }
}

@Composable
fun Actions(isMainScreen: Boolean, onButtonSearchClick: () -> Unit = {}) {
    if(isMainScreen) {
        IconButton(onClick = { onButtonSearchClick.invoke() }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More to show icon")
        }
    } else Box {}
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.onSecondary,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    )
}

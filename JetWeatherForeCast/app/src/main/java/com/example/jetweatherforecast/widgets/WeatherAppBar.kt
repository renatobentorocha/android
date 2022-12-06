package com.example.jetweatherforecast.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetweatherforecast.navigation.WeatherScreens


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
    val showDialog = remember {
        mutableStateOf(false)
    }

    if(showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

    TopAppBar(
        title = { Title(text = title)},
        actions = {
            Actions(
                isMainScreen,
                onButtonSearchClick = onAddActionClicked,
                onButtonMoreClick = {showDialog.value = true}) },
        navigationIcon = { NavigationAction(icon, onButtonClicked) },
        backgroundColor = Color.Transparent, 
        elevation = elevation)
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    val items = listOf("Favorites", "About", "Settings")
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = showDialog.value,
            onDismissRequest = { showDialog.value = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, s ->  
                DropdownMenuItem(onClick = { showDialog.value = false }) {
                    Row() {
                        Icon(
                            imageVector = when(s) {
                                "Favorites" -> Icons.Default.FavoriteBorder
                                "About" -> Icons.Default.Info
                                "Settings" -> Icons.Default.Settings
                                else -> {
                                    Icons.Default.Face
                                }
                            },
                            contentDescription = "${items[index]} icon",
                            tint = Color.LightGray
                        )
                        Text(
                            text = s,
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    route = when(s) {
                                    "Favorites" -> WeatherScreens.FavoriteScreen.name
                                    "About" -> WeatherScreens.AboutScreen.name
                                    "Settings" -> WeatherScreens.SettingsScreen.name
                                    else -> {
                                        WeatherScreens.MainScreen.name
                                    }
                                })
                            },
                            fontWeight = FontWeight.W300
                        )
                    }
                }
            }
        }
    }
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
fun Actions(isMainScreen: Boolean, onButtonSearchClick: () -> Unit = {}, onButtonMoreClick: () -> Unit = {}) {
    if(isMainScreen) {
        IconButton(onClick = { onButtonSearchClick.invoke() }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        }
        IconButton(onClick = { onButtonMoreClick.invoke() }) {
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

package com.example.jettrivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jettrivia.screens.QuestionViewModel
import com.example.jettrivia.ui.theme.JetTriviaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: QuestionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTriviaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TriviaHome(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun TriviaHome(viewModel: QuestionViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()

    Log.d("QTS", "Quantities: ${questions?.size}")

    Box{}
}

@Composable
fun Questions(viewModel: QuestionViewModel) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTriviaTheme {

    }
}
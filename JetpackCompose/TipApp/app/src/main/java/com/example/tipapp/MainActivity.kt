package com.example.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipapp.components.InputField
import com.example.tipapp.ui.theme.TipAppTheme
import com.example.tipapp.widgets.RoundIconButton

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                Column {
                    TopHeader()
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit){
    TipAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

//@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 123.0){
    val total = "$%.2f".format(totalPerPerson)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(
                shape = RoundedCornerShape(CornerSize(12.dp))
            ),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total per person",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = total,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@ExperimentalComposeUiApi
//@Preview
@Composable
fun MainContent(){
    BillForm() { billAmount ->

    }


}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App {
        TopHeader()
    }
}

@ExperimentalComposeUiApi
@Composable
fun BillForm(modifier: Modifier = Modifier, onValChange: (String) -> Unit = {}){
    val valueState = remember {
        mutableStateOf("")
    }

    val validState = remember(valueState.value) {
        valueState.value.trim().isNotEmpty()
    }

    val keyBoardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        )
    ) {
        Column {
            InputField(
                valueState = valueState,
                labelId = "Enter Bill",
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions

                    onValChange(valueState.value.toString().trim())

                    keyBoardController?.hide()
                }
            )
            
            if(validState) {
                CustomRow(
                    leading = {
                        Text(text = "Split")
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Remove Icon",
                            onClick = { /*TODO*/ }
                        )
                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Icon",
                            onClick = { /*TODO*/ }
                        )
                    }
                }
            } else {
                Box() {
                    
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun Test(){
    CustomRow(
        leading = {
            Text(text = "Text 1")
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundIconButton(
                imageVector = Icons.Default.Remove,
                contentDescription = "Remove Icon",
                onClick = { /*TODO*/ }
            )
            RoundIconButton(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                onClick = { /*TODO*/ }
            )
            Text(text = "Text 4")
        }
    }
}

@Composable
fun CustomRow(leading: @Composable () -> Unit, trailing: @Composable () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leading()
        trailing()
    }
}
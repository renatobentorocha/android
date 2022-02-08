package com.example.tipapp

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.tipapp.util.getTipValue
import com.example.tipapp.util.getTotalPerPerson
import com.example.tipapp.widgets.RoundIconButton
import java.text.DecimalFormat
import kotlin.math.max

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                Column {
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
@Composable
fun MainContent() {
    val totalBillState = remember {
        mutableStateOf("")
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    val validTotalBill = remember(totalBillState.value) {
        if (totalBillState.value.trim().isNotEmpty()) {
            totalBillState.value.toDouble()
        } else {
            0.0
        }
    }

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }

    val splitBy = remember {
        mutableStateOf(1)
    }

    TopHeader(getTotalPerPerson(
        validTotalBill,
        sliderPositionState.value.toDouble(),
        splitBy.value)
    )
    BillForm(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(top = 15.dp),
        totalBillState = totalBillState,
        validState = validState,
        sliderPositionState = sliderPositionState,
        splitBy = splitBy
    ) { billAmount -> }
}

@ExperimentalComposeUiApi
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    totalBillState: MutableState<String>,
    validState: Boolean,
    sliderPositionState: MutableState<Float>,
    splitBy: MutableState<Int>,
    onValChange: (String) -> Unit = {},
){
    val keyBoardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        )
    ) {
        Column {
            InputField(
                modifier = Modifier.padding(
                    bottom = 10.dp
                ),
                valueState = totalBillState,
                labelId = "Enter Bill",
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions

                    onValChange(totalBillState.value.toString().trim())

                    keyBoardController?.hide()
                }
            )
            
            if(validState) {
                Split(splitBy = splitBy)
                Tip(
                    getTipValue(
                        totalBillState.value.toDouble(),
                        sliderPositionState.value.toDouble()
                    )
                )
                TipSlider(sliderPositionState)
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
fun Split(splitBy: MutableState<Int> = mutableStateOf(1)){
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
                onClick = {
                    splitBy.value = max(1, splitBy.value - 1)
                }
            )
            Text(
                text = splitBy.value.toString(),
                modifier = Modifier
                    .padding(start = 9.dp, end = 9.dp)
                    .align(alignment = Alignment.CenterVertically),
            )
            RoundIconButton(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                onClick = {
                    splitBy.value += 1
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Tip(tip: Double = 33.00) {
    val decimal = DecimalFormat("#,###.00")

    CustomRow(
        modifier = Modifier.padding(vertical = 12.dp),
        leading = {
            Text(text = "Tip")
        }
    ) {
        Text(
            text = "$${decimal.format(tip)}",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TipSlider(sliderPosition: MutableState<Float> = mutableStateOf(0f)) {
    val tipPercentage = (sliderPosition.value * 100).toInt()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "$tipPercentage%")
        Slider(
            value = sliderPosition.value,
            onValueChange = {
                sliderPosition.value = it
            },
            steps = 5
        )
    }
}

@Composable
fun CustomRow(
    modifier: Modifier = Modifier,
    leading: @Composable () -> Unit,
    trailing: @Composable () -> Unit
) {
    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leading()
        trailing()
    }
}
package com.example.bmicalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmicalc.ui.theme.BMICalcTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMICalcTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding().verticalScroll(rememberScrollState()),
                    color = Color(0xFFfcf4f4)
                ) { MainScreen() }
            }
        }
    }
}


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf(0.0) }
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp).fillMaxSize()
    ) {
        Text(
            "Калькулятор",
            modifier = Modifier.padding(20.dp),
            color = Color(0xFF5777b4),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            "Для расчета ИМТ введите ваши значения роста и веса в поля ввода:",
            modifier = Modifier.padding(20.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            value = num2,
            onValueChange = {num2 = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text("Введите рост", style = MaterialTheme.typography.titleLarge)},
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color(0xFFFB9DAE)
            ),
            //focusedContainerColor = Color(0xFF5777b4),
            //cursorColor = Color(0xFF5777b4)
        )
        OutlinedTextField(
            value = num1,
            onValueChange = {num1 = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text("Введите вес", style = MaterialTheme.typography.titleLarge) },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color(0xFFFB9DAE)
            )
        )
        Spacer(modifier = Modifier.size(15.dp))
        Button(
            onClick = {
                result = if (num1.isNotEmpty() and num2.isNotEmpty()) {
                    (num1.toDouble()/((num2.toDouble()/100).pow(2.0)))
                } else if ((num1 == "0") or (num2 == "0")) {
                    0.0
                } else {
                    0.0
                }
            },
            modifier = Modifier.padding(15.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFF778E)),
            shape = RoundedCornerShape(15.dp),
        )
        {
            Text(
                "Рассчитать ИМТ",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(modifier = Modifier.size(15.dp))

        when(result) {
            in 0.1 .. 18.5 -> {
                ResultScreen(
                    resultBmi = roundTheNumber(result),
                    textLabelResourceId = R.string.deficit_weight,
                    descriptionResourceId = R.string.deficit_weight_desc
                )
            }
            in 18.5 .. 24.9 -> {
                ResultScreen(
                    resultBmi = roundTheNumber(result),
                    textLabelResourceId = R.string.normal_weight,
                    descriptionResourceId = R.string.normal_weight_desc
                )
            }
            in 25.0 .. 29.9 -> {
                ResultScreen(
                    resultBmi = roundTheNumber(result),
                    textLabelResourceId = R.string.overweight,
                    descriptionResourceId = R.string.overweight_desc
                )
            }
            in 30.0 .. 39.9 -> {
                ResultScreen(
                    resultBmi = roundTheNumber(result),
                    textLabelResourceId = R.string.obesity,
                    descriptionResourceId = R.string.obesity_desc
                )
            }
            in 40.0 .. 100.0 -> {
                ResultScreen(
                    resultBmi = roundTheNumber(result),
                    textLabelResourceId = R.string.morbid_obesity,
                    descriptionResourceId = R.string.morbid_obesity_desc
                )
            }
            0.0 -> {
                ResultScreen(
                    resultBmi = "0.0",
                    textLabelResourceId = R.string.no_values,
                    descriptionResourceId = R.string.no_values_desc
                )
            }
            else -> {
                ResultScreen(
                    resultBmi = "0.0",
                    textLabelResourceId = R.string.values_correct,
                    descriptionResourceId = R.string.values_correct_desc
                )
            }
        }
//        Text(
//            "Результат:    ${roundTheNumber(result)}",
//            modifier = Modifier.padding(20.dp),
//            style = MaterialTheme.typography.headlineSmall
//        )
//
        Spacer(modifier = Modifier.size(40.dp))
    }
}

// функция округления результата

fun roundTheNumber(numInDouble: Double): String {

    return "%.1f".format(numInDouble)
}


@Composable
fun ResultScreen(
    textLabelResourceId: Int,
    descriptionResourceId: Int,
    resultBmi: String,
    modifier: Modifier = Modifier

) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 280.dp, height = 300.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(20.dp).fillMaxSize()
        ) {
            Text(
                resultBmi,
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFFFB9DAE),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF5777b4),
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = stringResource(descriptionResourceId),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray,
                modifier = Modifier.padding(top = 10.dp)
            )

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMICalcTheme {
        MainScreen()
    }
}
package com.example.bmicalc.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmicalc.R
import kotlin.math.pow


@Composable
fun ResultsScreen(num1: String?, num2: String?) {

    var result by rememberSaveable { mutableStateOf(0.0) }

    result = BmiCalc(num1, num2)

//    result = if (num1.isNotEmpty() and num2.isNotEmpty()) {
//        (num1.toDouble() / ((num2.toDouble() / 100).pow(2.0)))
//    } else if ((num1 == "0") or (num2 == "0")) {
//        0.0
//    } else {
//        0.0
//    }

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

}

// формула ИМТ
fun BmiCalc(num1: String?, num2: String?): Double {

    return (num1!!.toDouble() / ((num2!!.toDouble() / 100).pow(2.0)))

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
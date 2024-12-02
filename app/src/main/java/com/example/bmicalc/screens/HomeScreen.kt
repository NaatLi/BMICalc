package com.example.bmicalc.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.math.pow

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable { mutableStateOf("") }

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
            onValueChange = { num2 = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text("Введите рост", style = MaterialTheme.typography.titleLarge) },
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
            onValueChange = { num1 = it },
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
                if (num1.isNotEmpty() and num2.isNotEmpty()) {
                    navController.navigate("results/$num1/$num2")
                } else {
                    num1 = "0"
                    num2 = "0"
                    navController.navigate("results/$num1/$num2")
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

    }

}


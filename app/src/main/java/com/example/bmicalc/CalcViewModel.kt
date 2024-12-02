package com.example.bmicalc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class CalcViewModel : ViewModel() {
    var result by mutableStateOf("")

    fun calcBmi(num1: String, num2: String) {
        result = try {
            roundTheNumber((num1.toDouble()/((num2.toDouble()/100).pow(2.0))))

        } catch (e: Exception) {
            "Не введено ни одного значения"
        }
    }
}
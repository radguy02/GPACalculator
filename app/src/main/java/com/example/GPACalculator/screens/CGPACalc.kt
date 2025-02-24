package com.example.GPACalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun CGPACalcScreen() {
    val semCredits = remember { mutableListOf(*Array(8){" "}) }
    val semGPA = remember { mutableListOf(*Array(8){" "})}

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ) {
        for(i in 0 until 8) {
            Row(modifier = Modifier){
                OutlinedTextField(
                    value = semCredits[i],
                    onValueChange = { input ->
                        if (input.matches(Regex("^(10(\\.0{1,2})?|[0-9](\\.\\d{1,2})?)$"))) {
                            semCredits[i] = input
                        }
                    },
                    label = { Text("Sem ${i + 1}") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                )

                OutlinedTextField(
                    value = semGPA[i],
                    onValueChange = { input ->
                        if (input.matches(Regex("^(10(\\.0{1,2})?|[0-9](\\.\\d{1,2})?)$"))) {
                            semCredits[i] = input
                        }
                    },
                    label = { Text("GPA") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
            }
        }
    }
}

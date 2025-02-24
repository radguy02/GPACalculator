package com.example.GPACalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun CGPACalcScreen() {
    val semCredits = remember { mutableListOf(*Array(8){" "}) }
    val semGPA = remember { mutableListOf(*Array(8){" "})}

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff121212))
        .padding(16.dp)
    ) {
        for(i in 0 until 8) {
            Row(modifier = Modifier){
                OutlinedTextField(
                    value = semCredits[i],
                    onValueChange = {semCredits[i] = it},
                    label = { Text("Sem ${i + 1}") },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )

                OutlinedTextField(
                    value = semGPA[i],
                    onValueChange = {semGPA[i] = it },
                    label = { Text("GPA") },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
            }
        }
    }
}

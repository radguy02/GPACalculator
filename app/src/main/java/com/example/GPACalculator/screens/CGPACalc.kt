package com.example.GPACalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CGPACalcScreen2() {
    val semCredits = remember { mutableStateListOf(*Array(8) { "" }) }
    val semGPA = remember { mutableStateListOf(*Array(8) { "" }) }
    var cgpa by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0 until 8) {
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = semCredits[i],
                    onValueChange = { input ->
                        if (input.isEmpty() || input.matches(Regex("^\\d+(\\.\\d{0,1})?$"))) {
                            semCredits[i] = input // ✅ Works correctly
                        }
                    },
                    label = { Text("Sem ${i + 1}", color = MaterialTheme.colorScheme.onBackground) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )

                OutlinedTextField(
                    value = semGPA[i],
                    onValueChange = { input ->
                        if (input.isEmpty() || input.matches(Regex("^(10(\\.0{0,2})?|[0-9](\\.\\d{0,2})?)?$"))){
                            semGPA[i] = input // ✅ Works correctly
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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                cgpa = calculateGpa(semCredits, semGPA)
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Calculate CGPA", color = MaterialTheme.colorScheme.onBackground)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your CGPA: ${String.format("%.2f", cgpa)}",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
    }
}

fun calculateGpa(credit: List<String>, gpa: List<String>): Double {
    var totalCredits = 0.0
    var weightedSum = 0.0

    for (i in credit.indices) {
        val credit = credit[i].toDoubleOrNull() ?: 0.0
        val grade = gpa[i].toDoubleOrNull() ?: 0.0

        totalCredits += credit
        weightedSum += credit * grade
    }

    return if (totalCredits > 0) weightedSum / totalCredits else 0.0
}
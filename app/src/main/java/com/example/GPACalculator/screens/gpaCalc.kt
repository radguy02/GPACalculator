
package com.example.GPACalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val creditOptions = listOf("1", "1.5", "2", "3", "4", "6") // Updated credits list
val gradeOptions = listOf("S", "A", "B", "C", "D", "E", "F", "N") // Grades list
val gradeValues = mapOf("S" to 10, "A" to 9, "B" to 8, "C" to 7, "D" to 6, "E" to 5, "F" to 0, "N" to 0)

@Preview
@Composable
fun GPACalculatorScreen2() {
    val selectedCredits = remember { mutableStateListOf(*Array(10) { "" }) }
    val selectedGrades = remember { mutableStateListOf(*Array(10) { "" }) }
    var gpa by remember { mutableStateOf(0.0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (i in 0 until 10) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Credit Dropdown
                var creditExpanded by remember { mutableStateOf(false) }
                Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                    OutlinedButton(
                        onClick = { creditExpanded = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        Text(text = selectedCredits[i].ifEmpty { "Credits" })
                    }

                    DropdownMenu(
                        expanded = creditExpanded,
                        onDismissRequest = { creditExpanded = false },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    ) {
                        creditOptions.forEach { credit ->
                            DropdownMenuItem(
                                text = { Text(credit, color = MaterialTheme.colorScheme.onBackground) },
                                onClick = {
                                    selectedCredits[i] = credit
                                    creditExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Grade Dropdown
                var gradeExpanded by remember { mutableStateOf(false) }
                Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                    OutlinedButton(
                        onClick = { gradeExpanded = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        Text(text = selectedGrades[i].ifEmpty { "Grade" })
                    }

                    DropdownMenu(
                        expanded = gradeExpanded,
                        onDismissRequest = { gradeExpanded = false },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    ) {
                        gradeOptions.forEach { grade ->
                            DropdownMenuItem(
                                text = { Text(grade, color = MaterialTheme.colorScheme.onBackground) },
                                onClick = {
                                    selectedGrades[i] = grade
                                    gradeExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                gpa = calculateCGPA(selectedCredits, selectedGrades, gradeValues)
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Calculate GPA", color = MaterialTheme.colorScheme.onBackground)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Your CGPA: ${String.format("%.2f", gpa)}", color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp)
    }
}

fun calculateCGPA(credits: List<String>, grades: List<String>, gradeValues: Map<String, Int>): Double {
    var totalCredits = 0.0
    var weightedSum = 0.0

    for (i in credits.indices) {
        val credit = credits[i].toDoubleOrNull() ?: 0.0
        val grade = gradeValues[grades[i]] ?: 0

        totalCredits += credit
        weightedSum += credit * grade
    }

    return if (totalCredits > 0) weightedSum / totalCredits else 0.0
}

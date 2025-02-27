
package com.example.GPACalculator.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController

sealed class Screen(val route: String) {
    object GPA : Screen("gpa_screen")
    object CGPA : Screen("cgpa_screen")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.GPA.route  // Default start destination
    ) {
        composable(Screen.GPA.route) { GPACalculatorScreen2() }
        composable(Screen.CGPA.route) { CGPACalcScreen2() }
    }
}


package com.example.GPACalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.GPACalculator.ui.theme.RADGuyTheme
import com.example.GPACalculator.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RADGuyTheme {
                Scaffold { paddingValues ->
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF121212))
                            .fillMaxSize()
                            .padding(paddingValues), // ✅ Auto-adjusts for system bars
                        contentAlignment = Alignment.Center
                    ) {
                        GPACalculatorScreen2()
                    }
                }
            }
        }
    }
}


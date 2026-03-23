package com.example.calculatocalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.calculatocalculator.ui.theme.CalculatorScreen
import com.example.calculatocalculator.viewModel.CalculatorViewModel

class MainActivity : ComponentActivity() {

    val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorScreen(state = viewModel.state, onButtonClick = { viewModel.onButtonClick(it)})

        }
    }
}
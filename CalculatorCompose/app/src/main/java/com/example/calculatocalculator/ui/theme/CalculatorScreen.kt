package com.example.calculatocalculator.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatocalculator.model.Calculator
import com.example.calculatocalculator.viewModel.CalculatorViewModel

@Composable
fun CalculatorScreen(state: Calculator, onButtonClick: (String) -> Unit) {

    Column(modifier = Modifier.fillMaxSize().background(color = Color.Black).padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.fillMaxWidth().padding(top = 40.dp), horizontalAlignment = Alignment.End) {
            Text(text = state.expression, color = Color.White, fontSize = 28.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = state.result,color = Color.White, fontSize = 48.sp)
        }
        val buttons = listOf(
            listOf("C","(",")","/"),
            listOf("7","8","9","*"),
            listOf("4","5","6","-"),
            listOf("1","2","3","+"),
            listOf(".","0","=")
        )

        Column {
            buttons.forEach { row->
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.spacedBy(8.dp)   ) {
                    row.forEach { symbol ->
                        CalculatorButton(symbol) { onButtonClick(symbol) }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showSystemUi = true)
@Composable
fun CalculatorPreview() {
    CalculatorScreen(
        state = Calculator("", ""),
        onButtonClick = {}
    )
}

@Composable
fun RowScope.CalculatorButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier
        .padding(6.dp)
        .weight(if(text == "=") 2f else 1f)
        .height(76.dp), shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = when (text) {
                "=" -> Color(0xFF1BD315)
                "+", "-", "*", "/" -> Color(0xFFFF9800)
                "C" -> Color.Red
                else -> Color.DarkGray
            }
        )
    ) {
        Text(text = text, fontSize = 32.sp, color = Color.White)
    }
}

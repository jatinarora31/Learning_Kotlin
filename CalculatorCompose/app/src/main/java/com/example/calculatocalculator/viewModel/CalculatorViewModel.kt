package com.example.calculatocalculator.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.calculatocalculator.model.Calculator

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(Calculator())
        private set

    fun onButtonClick(value: String) {
        when (value) {
            "C" -> state = Calculator()

            "=" -> calculateResult()

            else -> {
                state = state.copy(
                    expression = state.expression + value
                )
            }
        }
    }

    private fun calculateResult() {
        try {
            val result = eval(state.expression)
            state = state.copy(result = result.toString())
        } catch (e: Exception) {
            state = state.copy(result = "Error")
        }
    }

    private fun eval(expr: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < expr.length) expr[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm()
                        eat('-'.code) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.code) -> x *= parseFactor()
                        eat('/'.code) -> x /= parseFactor()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()

                var x: Double
                val startPos = pos

                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else {
                    while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                    x = expr.substring(startPos, pos).toDouble()
                }

                return x
            }
        }.parse()
    }
}
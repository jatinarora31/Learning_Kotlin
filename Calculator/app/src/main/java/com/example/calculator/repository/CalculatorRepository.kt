package com.example.calculator.repository

import com.example.calculator.util.ExpressionEvaluator

class CalculatorRepository {

    fun calculate(expression: String): Int {
        return ExpressionEvaluator.evaluate(expression)
    }

}
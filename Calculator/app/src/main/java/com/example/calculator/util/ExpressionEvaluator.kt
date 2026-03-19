package com.example.calculator.util

object ExpressionEvaluator {

    fun evaluate(expr: String): Int {
        var expression = expr

        while (expression.contains("*")) {
            val regex = Regex("(\\d+)\\*(\\d+)")
            val match = regex.find(expression)
            if (match != null) {
                val res = match.groupValues[1].toInt() * match.groupValues[2].toInt()
                expression = expression.replaceFirst(regex, res.toString())
            }
        }

        while (expression.contains("/")) {
            val regex = Regex("(\\d+)/(\\d+)")
            val match = regex.find(expression)
            if (match != null) {
                val res = match.groupValues[1].toInt() / match.groupValues[2].toInt()
                expression = expression.replaceFirst(regex, res.toString())
            }
        }

        while (expression.contains("+")) {
            val regex = Regex("(\\d+)\\+(\\d+)")
            val match = regex.find(expression)
            if (match != null) {
                val res = match.groupValues[1].toInt() + match.groupValues[2].toInt()
                expression = expression.replaceFirst(regex, res.toString())
            }
        }

        while (expression.contains("-")) {
            val regex = Regex("(\\d+)-(\\d+)")
            val match = regex.find(expression)
            if (match != null) {
                val res = match.groupValues[1].toInt() - match.groupValues[2].toInt()
                expression = expression.replaceFirst(regex, res.toString())
            }
        }

        return expression.toInt()
    }
}
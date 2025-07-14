package com.watsidev.producto2.presentation.screens.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var uiState by mutableStateOf(CalculatorUiState())
        private set

    fun onAction(action: String) {
        when (action) {
            "=" -> calculateResult()
            "C" -> uiState = CalculatorUiState()
            "⌫" -> uiState = uiState.copy(expression = uiState.expression.dropLast(1))
            else -> uiState = uiState.copy(expression = uiState.expression + action)
        }
    }

    private fun calculateResult() {
        try {
            val expr = uiState.expression
                .replace("×", "*")
                .replace("÷", "/")
                .replace("−", "-") // ← este es el fix
                .replace("%", "/100")
                .replace("√", "sqrt")
                .replace("x²", "^2")

            // Reemplazar "^2" por "**2" si se usa exp evaluator compatible
            val result = ExpressionEvaluator.evaluate(expr)
            uiState = uiState.copy(result = result.toString())
        } catch (e: Exception) {
            uiState = uiState.copy(result = "Error")
        }
    }
}

package com.watsidev.producto2.presentation.screens.calculator

import net.objecthunter.exp4j.ExpressionBuilder

object ExpressionEvaluator {
    fun evaluate(expr: String): Double {
        val expression = ExpressionBuilder(expr).build()
        return expression.evaluate()
    }
}

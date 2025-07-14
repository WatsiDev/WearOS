package com.watsidev.producto2.presentation.screens.game

data class CombateState(
    val vidaPropia: Float = 1f,
    val vidaEnemigo: Float = 1f,
    val mostrarOpciones: Boolean = false,
    val mensajeFinal: String? = null
)

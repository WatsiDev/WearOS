package com.watsidev.producto2.presentation.screens.temporizer

data class TimerUiState(
    val remainingTime: Long = 60L, // segundos
    val isRunning: Boolean = false
)

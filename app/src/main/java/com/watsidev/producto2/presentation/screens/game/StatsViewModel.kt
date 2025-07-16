package com.watsidev.producto2.presentation.screens.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class StatsViewModel : ViewModel() {

    private val _stats = MutableStateFlow(StatsUiState())
    val stats: StateFlow<StatsUiState> = _stats

    fun actualizarStats(mensaje: String) {
        _stats.update { current ->
            val gano = mensaje == "¡Ganaste!"
            current.copy(
                totalCombates = current.totalCombates + 1,
                victorias = current.victorias + if (gano) 1 else 0,
                puntos = current.puntos + if (gano) 100 else 0
            )
        }
    }

    fun resetear() {
        _stats.value = StatsUiState()
    }
}

package com.watsidev.producto2.presentation.screens.stepCounter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.compose.runtime.State

class StepCounterViewModel : ViewModel() {

    private val _uiState = mutableStateOf(StepCounterUiState())
    val uiState: State<StepCounterUiState> = _uiState

    private var simulationJob: Job? = null

    fun startSimulation() {
        if (_uiState.value.isSimulating) return

        _uiState.value = _uiState.value.copy(isSimulating = true)

        simulationJob = viewModelScope.launch {
            while (isActive) {
                delay(1000L) // cada segundo simula un paso
                _uiState.value = _uiState.value.copy(
                    steps = _uiState.value.steps + Random.nextInt(1, 3)
                )
            }
        }
    }

    fun stopSimulation() {
        simulationJob?.cancel()
        _uiState.value = _uiState.value.copy(isSimulating = false)
    }

    fun incrementStep() {
        _uiState.value = _uiState.value.copy(steps = _uiState.value.steps + 1)
    }

    fun reset() {
        simulationJob?.cancel()
        _uiState.value = StepCounterUiState()
    }
}

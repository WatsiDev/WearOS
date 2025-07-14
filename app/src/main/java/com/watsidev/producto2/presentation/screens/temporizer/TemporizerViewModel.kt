package com.watsidev.producto2.presentation.screens.temporizer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

class TimerViewModel : ViewModel() {

    private val _uiState = mutableStateOf(TimerUiState())
    val uiState: State<TimerUiState> = _uiState

    private var timerJob: Job? = null

    fun startTimer() {
        if (_uiState.value.isRunning) return

        _uiState.value = _uiState.value.copy(isRunning = true)

        timerJob = viewModelScope.launch {
            while (_uiState.value.remainingTime > 0) {
                delay(1000L)
                _uiState.value = _uiState.value.copy(
                    remainingTime = _uiState.value.remainingTime - 1
                )
            }
            _uiState.value = _uiState.value.copy(isRunning = false)
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(isRunning = false)
    }

    fun resetTimer() {
        timerJob?.cancel()
        _uiState.value = TimerUiState() // reinicia a 60s
    }

    fun addTime(seconds: Long = 10L) {
        _uiState.value = _uiState.value.copy(
            remainingTime = _uiState.value.remainingTime + seconds
        )
    }
}

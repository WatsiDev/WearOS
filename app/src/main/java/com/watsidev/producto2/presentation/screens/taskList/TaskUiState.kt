package com.watsidev.producto2.presentation.screens.taskList

import com.watsidev.producto2.complication.data.model.Task

sealed class TaskUiState {
    object Loading : TaskUiState()
    data class Success(val tasks: List<Task>) : TaskUiState()
    data class Error(val message: String) : TaskUiState()
}

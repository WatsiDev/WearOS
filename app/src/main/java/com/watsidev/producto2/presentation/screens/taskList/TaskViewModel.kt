package com.watsidev.producto2.presentation.screens.taskList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watsidev.producto2.complication.network.RetrofitClient
import com.watsidev.producto2.complication.network.TaskRequest
import com.watsidev.producto2.complication.network.TaskResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val _tasksByColumn = MutableStateFlow<List<TaskResponse>>(emptyList())
    val tasksByColumn: StateFlow<List<TaskResponse>> = _tasksByColumn

    fun loadTasksByColumn() {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.api.getTasksByColumn(columnId = 1)
                _tasksByColumn.value = result
            } catch (e: Exception) {
                Log.e("TaskVM", "Error cargando tareas columna 1: ${e.message}")
            }
        }
    }

    fun createTask(task: TaskRequest) {
        viewModelScope.launch {
            try {
                RetrofitClient.api.createTask(task)
                loadTasksByColumn() // refrescar después de crear
            } catch (e: Exception) {
                Log.e("TaskVM", "Error creando tarea: ${e.message}")
            }
        }
    }
}

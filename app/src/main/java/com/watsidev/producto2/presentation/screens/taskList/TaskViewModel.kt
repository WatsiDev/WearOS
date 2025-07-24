package com.watsidev.producto2.presentation.screens.taskList

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watsidev.producto2.complication.data.model.Priority
import com.watsidev.producto2.complication.data.model.StatusUpdate
import com.watsidev.producto2.complication.data.model.Task
import com.watsidev.producto2.complication.data.model.TaskRequest
import com.watsidev.producto2.complication.data.model.UpdateTask
import com.watsidev.producto2.complication.network.RetrofitClient
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    var taskUiState by mutableStateOf<TaskUiState>(TaskUiState.Loading)
        private set

    var priorities by mutableStateOf<List<Priority>>(emptyList())
        private set

    // Guardamos los IDs de prioridades seleccionadas
    var selectedPriorities by mutableStateOf<Set<Int>>(emptySet())
        private set

    var selectedTask by mutableStateOf<UpdateTask?>(null)
        private set

    private val api = RetrofitClient.api

    init {
        fetchAllTasks()
        fetchPriorities()
    }

    fun fetchAllTasks() {
        viewModelScope.launch {
            taskUiState = TaskUiState.Loading
            try {
                val tasks = api.getAllTasks()
                taskUiState = TaskUiState.Success(tasks as List<Task>)
            } catch (e: Exception) {
                taskUiState = TaskUiState.Error("Error al cargar tareas: ${e.localizedMessage}")
            }
        }
    }

    fun fetchPriorities() {
        viewModelScope.launch {
            try {
                priorities = api.getPriorities() as List<Priority>
            } catch (e: Exception) {
                // Manejar error si es necesario
            }
        }
    }

    // NUEVA: Lógica para seleccionar/deseleccionar prioridad
    fun togglePrioritySelection(priorityId: Int) {
        selectedPriorities = if (selectedPriorities.contains(priorityId)) {
            selectedPriorities - priorityId
        } else {
            selectedPriorities + priorityId
        }

        // Si no hay prioridades seleccionadas, mostramos todas las tareas
        if (selectedPriorities.isEmpty()) {
            fetchAllTasks()
        } else {
            fetchTasksByPriorities(selectedPriorities.toList())
        }
    }

    // NUEVA: Obtener tareas filtradas por las prioridades seleccionadas
    private fun fetchTasksByPriorities(priorityIds: List<Int>) {
        viewModelScope.launch {
            taskUiState = TaskUiState.Loading
            try {
                // Combinar los resultados de todas las prioridades seleccionadas
                val tasks = mutableListOf<Task>()
                for (id in priorityIds) {
                    val tasksByPriority = api.getTasksByPriority(id)
                    tasks.addAll(tasksByPriority)
                }
                taskUiState = TaskUiState.Success(tasks)
            } catch (e: Exception) {
                taskUiState = TaskUiState.Error("Error al filtrar tareas: ${e.localizedMessage}")
            }
        }
    }

    fun createTask(title: String, description: String?, priorityId: Int, dateLimit: String) {
        viewModelScope.launch {
            try {
                val request = TaskRequest(title, description, priorityId, dateLimit)
                api.createTask(request)
                fetchAllTasks()
            } catch (e: Exception) {
                Log.i("Task", "No se ha creado la tarea: ${e.message}")
            }
        }
    }

    fun getTaskById(id: Int) {
        viewModelScope.launch {
            try {
                val task = RetrofitClient.api.getTaskById(id)
                selectedTask = task
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error al obtener tarea por id: ${e.message}")
            }
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            try {
                api.deleteTask(taskId)
                fetchAllTasks()
            } catch (e: Exception) {
                // manejar error si quieres
            }
        }
    }

    fun updateTask(
        id: Int,
        title: String,
        description: String?,
        priorityId: Int,
        dateLimit: String
    ) {
        viewModelScope.launch {
            try {
                val taskRequest = TaskRequest(
                    title = title,
                    description = description,
                    priority_id = priorityId,
                    date_limit = dateLimit,
                )

                val response = api.updateTask(id, taskRequest)
                if (response.message == "Tarea actualizada exitosamente") {
                    Log.d("TaskViewModel", "Tarea actualizada correctamente")
                    // podrías recargar tareas aquí si lo deseas
                } else {
                    Log.e("TaskViewModel", "Error al actualizar tarea: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Excepción al actualizar tarea", e)
            }
        }
    }

    fun toggleTaskCompleted(task: Task) {
        viewModelScope.launch {
            try {
                api.updateTaskStatus(task.id, StatusUpdate(!task.isCompleted))
                fetchAllTasks()
            } catch (e: Exception) {
                // manejar error si quieres
            }
        }
    }
}

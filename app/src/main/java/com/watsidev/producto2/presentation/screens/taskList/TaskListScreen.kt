package com.watsidev.producto2.presentation.screens.taskList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import com.watsidev.producto2.complication.data.model.Task

@Composable
fun TaskListScreen(
    viewModel: TaskViewModel = viewModel(),
    onAddTask: (Int?) -> Unit = {},
    onEditTask: (Int) -> Unit = {}
) {
    val uiState = viewModel.taskUiState
    val priorities = viewModel.priorities
    val selectedPriorities = viewModel.selectedPriorities
    var showFilters by remember { mutableStateOf(false) }

    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la app
        item {
            Text(
                text = "ToDo App",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Fila de botones (filtrar / agregar)
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                IconButton(onClick = { showFilters = !showFilters }) {
                    Icon(
                        imageVector = Icons.Outlined.FilterAlt,
                        contentDescription = "Filtrar",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { onAddTask(null) }) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Agregar tarea",
                        tint = Color.White
                    )
                }
            }
        }

        // Panel de filtros con AnimatedVisibility
        item {
            AnimatedVisibility(visible = showFilters) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    priorities.forEach { priority ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                        ) {
                            Checkbox(
                                checked = selectedPriorities.contains(priority.id),
                                onCheckedChange = {
                                    viewModel.togglePrioritySelection(priority.id)
                                }
                            )
                            Text(
                                text = priority.priority,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        // Contenido según el estado
        when (uiState) {
            is TaskUiState.Loading -> {
                item {
                    CircularProgressIndicator()
                }
            }

            is TaskUiState.Error -> {
                item {
                    Text(
                        text = uiState.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            is TaskUiState.Success -> {
                items(uiState.tasks) { task ->
                    TaskCard(
                        priorityColor = when (task.priority) {
                            "Alta" -> Color(0xFF872828)
                            "Media" -> Color(0xFFA58541)
                            else -> Color(0xFF3F6D2A)
                        },
                        bgColor = when (task.priority) {
                            "Alta" -> Color(0xFFEFBBBB)
                            "Media" -> Color(0xFFF8E6C4)
                            else -> Color(0xFFCDF3C8)
                        },
                        task = task,
                        onEdit = { onEditTask(it) },
                        onDelete = { viewModel.deleteTask(task.id) },
                        onToggleCompleted = { viewModel.toggleTaskCompleted(task) }
                    )
                }
            }
        }
    }
}


@Composable
fun TaskCard(
    priorityColor: Color,
    bgColor: Color,
    task: Task,
    onEdit: (Int) -> Unit,
    onDelete: () -> Unit,
    onToggleCompleted: () -> Unit
) {
    Row(
        modifier = Modifier
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onToggleCompleted() }
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                task.priority,
                color = priorityColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(bgColor)
                    .padding(8.dp, 3.dp)
            )
            Text(
                task.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            task.description?.let {
                Text(
                    it,
                    fontSize = 12.sp
                )
            }
            Text(
                task.dateLimit
            )
        }
        Column(
            modifier = Modifier
        ) {
            IconButton(onClick = { onEdit(task.id) }) {
                Icon(Icons.Outlined.Edit, contentDescription = "Editar")
            }
            IconButton(onClick = { onDelete() }) {
                Icon(Icons.Outlined.Delete, contentDescription = "Eliminar")
            }
        }
    }
}
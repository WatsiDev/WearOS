package com.watsidev.producto2.presentation.screens.taskList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items

@Composable
fun TaskListScreen(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasksByColumn.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTasksByColumn()
    }

    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Lista de tareas",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        if (tasks.isEmpty()) {
            item {
                Text("No hay tareas disponibles", style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    onClick = {}
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Prioridad: ${task.priority}", style = MaterialTheme.typography.labelMedium)
                        Text(task.title, style = MaterialTheme.typography.bodyLarge)
                        Text(task.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

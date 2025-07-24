package com.watsidev.producto2.presentation.screens.taskList

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import com.watsidev.producto2.complication.data.model.Priority
import com.watsidev.producto2.complication.data.model.Task
import java.util.Calendar


@Composable
fun TaskScreen(
    viewModel: TaskViewModel = viewModel(),
    onTaskSaved: () -> Unit,
    id: Int?
) {
    val context = LocalContext.current
    val priorities = viewModel.priorities
    val selectedTask = viewModel.selectedTask

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var selectedPriority by remember { mutableStateOf<Priority?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var dateLimit by remember { mutableStateOf("") }
    var wasInitialized by remember { mutableStateOf(false) }

    // Obtener tarea por ID si estamos editando
    LaunchedEffect(id) {
        if (id != null) {
            viewModel.getTaskById(id)
        }
    }

    // Inicializar campos con la tarea una vez
    LaunchedEffect(selectedTask) {
        if (id != null && selectedTask != null && !wasInitialized) {
            title = TextFieldValue(selectedTask.title)
            description = TextFieldValue(selectedTask.description ?: "")
            wasInitialized = true
        }
    }


    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        item {
            Text(
                text = if (id == null) "Crear Tarea" else "Editar Tarea",
                modifier = Modifier.padding(8.dp),
                color = Color.White
            )
        }

        // Campo Title
        item {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }

        // Campo Description
        item {
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }

        // Dropdown Priority
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = selectedPriority?.priority ?: "Seleccionar prioridad",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                        .padding(8.dp)
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    containerColor = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    priorities.forEach { priority ->
                        DropdownMenuItem(
                            text = { Text(priority.priority, color = Color.Black) },
                            onClick = {
                                selectedPriority = priority
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        // Date Limit con DatePicker
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .background(Color.White)
                    .clickable {
                        showDatePicker(context = context) { selectedDate ->
                            dateLimit = selectedDate
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (dateLimit.isEmpty()) "Seleccionar fecha límite" else dateLimit
                )
            }
        }

        // Botón Guardar
        item {
            val isFormValid = title.text.isNotBlank() && selectedPriority != null && dateLimit.isNotEmpty()
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                enabled = isFormValid,
                onClick = {
                    if (id == null) {
                        viewModel.createTask(
                            title = title.text,
                            description = description.text.ifBlank { null },
                            priorityId = selectedPriority?.id ?: 1,
                            dateLimit = dateLimit,
                        )
                    } else {
                        viewModel.updateTask(
                            id = id,
                            title = title.text,
                            description = description.text.ifBlank { null },
                            priorityId = selectedPriority?.id ?: 1,
                            dateLimit = dateLimit,
                        )
                    }
                    onTaskSaved()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    android.app.DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            onDateSelected(formattedDate)
        },
        year, month, day
    ).show()
}

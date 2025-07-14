package com.watsidev.producto2.presentation.screens.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Área de resultado
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = state.expression,
                fontSize = 20.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = state.result,
                fontSize = 28.sp,
                color = Color(0xFFF2F2F2),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        // Botonera (limitamos a 3-4 botones por fila)
        val buttons = listOf(
            listOf("(", ")", "C", "⌫"),
            listOf("7", "8", "9", "÷"),
            listOf("4", "5", "6", "×"),
            listOf("1", "2", "3", "−"),
            listOf("0", ".", "=", "+"),
            listOf("√", "x²", "%")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    CalculatorButton(label) {
                        viewModel.onAction(label)
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(44.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF2F2F2),
            contentColor = Color(0xFF2E2E2E)
        ),
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(2.dp)
        )
    }
}
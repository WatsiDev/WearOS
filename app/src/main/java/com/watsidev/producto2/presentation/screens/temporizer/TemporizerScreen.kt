package com.watsidev.producto2.presentation.screens.temporizer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import com.watsidev.producto2.presentation.navigation.Temporizer

@Composable
fun TemporizerScreen(viewModel: TimerViewModel = viewModel()) {
    val state by viewModel.uiState
    val minutes = state.remainingTime / 60
    val seconds = state.remainingTime % 60

    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Text(
                text = String.format("%02d:%02d", minutes, seconds),
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                IconButton(onClick = {
                    if (state.isRunning) viewModel.pauseTimer()
                    else viewModel.startTimer()
                }) {
                    Icon(
                        imageVector = if (state.isRunning) Icons.Outlined.Pause else Icons.Outlined.PlayArrow,
                        contentDescription = "Play/Pause",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                IconButton(onClick = { viewModel.resetTimer() }) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = "Reset",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        item {
            Text(
                text = "Agregar tiempo",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )
        }

        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Button(
                    onClick = { viewModel.addTime(600) }, // 10 minutos
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("+10 min")
                }

                Button(
                    onClick = { viewModel.addTime(900) }, // 15 minutos
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("+15 min")
                }

                Button(
                    onClick = { viewModel.addTime(1800) }, // 30 minutos
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("+30 min")
                }
            }
        }
    }
}

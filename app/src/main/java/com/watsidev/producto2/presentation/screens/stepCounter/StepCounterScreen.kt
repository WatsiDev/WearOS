package com.watsidev.producto2.presentation.screens.stepCounter

import android.R.attr.contentDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlusOne
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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

@Composable
fun StepCounterScreen(viewModel: StepCounterViewModel = viewModel()) {
    val state by viewModel.uiState

    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.DirectionsWalk,
                contentDescription = "Pasos",
                modifier = Modifier.size(48.dp),
                tint = Color.White
            )
        }

        item {
            Text(
                text = "${state.steps} pasos",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(modifier = Modifier.padding(top = 12.dp)) {
                Button(
                    onClick = {
                        if (state.isSimulating) viewModel.stopSimulation()
                        else viewModel.startSimulation()
                    }
                ) {
                    Icon(
                        if (state.isSimulating) Icons.Outlined.Pause else Icons.AutoMirrored.Filled.DirectionsWalk,
                        contentDescription = if (state.isSimulating) "Detener Simulación" else "Iniciar Simulación"
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = { viewModel.incrementStep() }) {
                    Icon(
                        imageVector = Icons.Outlined.PlusOne,
                        contentDescription = "Incrementar Pasos"
                    )
                }
            }
        }

        item {
            Button(
                onClick = { viewModel.reset() },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.RestartAlt,
                    contentDescription = "Reiniciar Contador",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

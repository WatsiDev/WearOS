package com.watsidev.producto2.presentation.screens.heartRate

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import kotlinx.coroutines.delay

@Composable
fun HeartRateScreen() {
    val bpm = remember { mutableStateOf(72) } // bpm inicial
    val scale = remember { Animatable(1f) }

    // Simula un nuevo latido y bpm cada 2 segundos
    LaunchedEffect(Unit) {
        while (true) {
            val newBpm = (60..100).random()
            bpm.value = newBpm

            scale.animateTo(
                1.2f,
                animationSpec = tween(durationMillis = 200, easing = LinearEasing)
            )
            scale.animateTo(
                1f,
                animationSpec = tween(durationMillis = 200, easing = LinearEasing)
            )

            delay(2000L) // esperar 2 segundos entre latidos
        }
    }

    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        item {
            Text(
                text = "Frecuencia Cardíaca",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Latido",
                modifier = Modifier
                    .size(80.dp)
                    .graphicsLayer {
                        scaleX = scale.value
                        scaleY = scale.value
                    },
                tint = Color.Red
            )
        }

        item {
            Text(
                text = "${bpm.value} bpm",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

package com.watsidev.producto2.presentation.screens.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BarraVida(vida: Float) {
    LinearProgressIndicator(
    progress = { vida },
    modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(6.dp),
    color = Color.Red,
    trackColor = ProgressIndicatorDefaults.linearTrackColor,
    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
    )
}

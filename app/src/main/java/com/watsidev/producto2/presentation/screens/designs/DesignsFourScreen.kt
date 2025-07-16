package com.watsidev.producto2.presentation.screens.designs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClockStepsScreen(steps: Int = 7300, goal: Int = 8000, onClick:() -> Unit) {
    val progress = steps.toFloat() / goal

    Box(
        modifier = Modifier
            .clickable{ onClick() }
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Clock hands (mockup, static)
            drawLine(
                color = Color.White,
                start = center,
                end = Offset(center.x, center.y - size.minDimension / 3),
                strokeWidth = 4f
            )
            drawLine(
                color = Color(0xFFCCFF00),
                start = center,
                end = Offset(center.x + size.minDimension / 4, center.y),
                strokeWidth = 8f
            )
        }
        Box(modifier = Modifier.align(Alignment.Center)) {
            Surface(
                shape = CircleShape,
                color = Color(0xFFCCFF00),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("${steps / 1000}K", color = Color.Black, fontSize = 14.sp)
                }
            }
        }
    }
}

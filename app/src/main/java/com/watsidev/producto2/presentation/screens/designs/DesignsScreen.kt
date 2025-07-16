package com.watsidev.producto2.presentation.screens.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text

@Composable
fun DesignsScreen(
    steps: Int = 6562, goal: Int = 8000, onClick: () -> Unit) {
    val progress = steps.toFloat() / goal

    Box(
        modifier = Modifier
            .clickable{ onClick() }
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progress,
            strokeWidth = 6.dp,
            color = Color(0xFFCCFF00),
            modifier = Modifier.size(180.dp)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Steps", color = Color.White, fontSize = 16.sp)
            Text(
                text = "$steps",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "/ $goal",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}

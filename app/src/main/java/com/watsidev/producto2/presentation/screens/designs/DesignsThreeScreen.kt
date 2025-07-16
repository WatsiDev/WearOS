package com.watsidev.producto2.presentation.screens.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text

@Composable
fun YogaScreen(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable{ onClick() }
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Power Yoga", color = Color.White, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { /* Start Yoga */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCCFF00))
        ) {
            Text(text = "Start", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Last session 45m", color = Color.Gray, fontSize = 14.sp)
    }
}

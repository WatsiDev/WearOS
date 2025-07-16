package com.watsidev.producto2.presentation.screens.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsBike
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text

@Composable
fun ActivitiesScreen(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable{ onClick() }
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "1 run this week", color = Color.White, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            IconButton(onClick = { /* Run */ }) {
                Icon(Icons.AutoMirrored.Filled.DirectionsRun, contentDescription = null, tint = Color(0xFFCCFF00))
            }
            IconButton(onClick = { /* Walk */ }) {
                Icon(Icons.AutoMirrored.Filled.DirectionsWalk, contentDescription = null, tint = Color(0xFFCCFF00))
            }
            IconButton(onClick = { /* Bike */ }) {
                Icon(Icons.AutoMirrored.Filled.DirectionsBike, contentDescription = null, tint = Color(0xFFCCFF00))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /* More */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ) {
            Text(text = "More", color = Color.White)
        }
    }
}

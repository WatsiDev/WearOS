package com.watsidev.producto2.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Watch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text

@Composable
fun SettingsScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Watch,
            contentDescription = "Settings Icon",
        )
        Text(
            "Your watch is up to date",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            "Android version 15",
            fontSize = 10.sp
        )
        Text(
            "Android security update:",
            fontSize = 10.sp
        )
        Text(
            "December 5, 2024",
            fontSize = 10.sp
        )
    }
}
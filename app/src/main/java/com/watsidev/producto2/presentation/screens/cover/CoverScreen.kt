package com.watsidev.producto2.presentation.screens.cover

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.watsidev.producto2.R
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CoverScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick() }
    ) {
        // Fondo
        Image(
            painterResource(R.drawable.cover_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Reloj centrado
        ClockTime(
            modifier = Modifier
                .align(Alignment.Center)
        )

        // Texto de derechos reservados al fondo
        Text(
            "All rights reserved ©",
            fontSize = 10.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}


@Composable
fun ClockTime(
    modifier: Modifier = Modifier
) {
    val currentTime = remember { mutableStateOf(LocalDateTime.now()) }
    val toggleFormat = remember { mutableStateOf(true) }

    // Efecto para actualizar la hora cada segundo y alternar el formato de la fecha
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000L)
            currentTime.value = LocalDateTime.now()
            toggleFormat.value = !toggleFormat.value
        }
    }

    val time = currentTime.value
    val hour = time.format(DateTimeFormatter.ofPattern("HH"))
    val minute = time.format(DateTimeFormatter.ofPattern("mm"))

//    val locale = Locale("es", "MX")
    val dateText = if (toggleFormat.value) {
        time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    } else {
        time.format(DateTimeFormatter.ofPattern("EEEE, MMMM")).replaceFirstChar { it.uppercase() }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = hour,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = minute,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedContent(
            targetState = dateText,
            transitionSpec = {
                fadeIn(tween(300)) togetherWith fadeOut(tween(300))
            }
        ) { animatedDate ->
            Text(
                text = animatedDate,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
        }
    }
}
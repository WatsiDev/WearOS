package com.watsidev.producto2.presentation.screens.maps

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.watsidev.producto2.R
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CompassApp() {
    var angle by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            val baseAngle = angle
            val oscillation = (-10..10).random()
            val newAngle = (baseAngle + oscillation).coerceIn(0f, 360f)

            if ((0..4).random() == 0) {
                val jump = listOf(-60f, 60f).random()
                angle = (baseAngle + jump).mod(360f)
            } else {
                angle = newAngle
            }

            delay(600L)
        }
    }

    CompassScreen(angle)
}

@Composable
fun CompassScreen(angle: Float) {
    val animatedAngle by animateFloatAsState(
        targetValue = angle,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing),
        label = "NeedleRotation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color.DarkGray, Color.Black),
                    center = Offset.Unspecified,
                    radius = 500f
                )),
        contentAlignment = Alignment.Center
    ) {
        CompassDial()
        CompassNeedle(animatedAngle)
    }
}

@Composable
fun CompassDial() {
    Canvas(modifier = Modifier.size(250.dp)) {
        val center = size / 2f
        val radius = size.minDimension / 2f

        drawCircle(
            color = Color.White.copy(alpha = 0.1f),
            style = Stroke(width = 4.dp.toPx()),
            radius = radius
        )

        // Dibuja marcas cada 45°
        for (i in 0 until 360 step 45) {
            val angleRad = Math.toRadians(i.toDouble())
            val lineLength = if (i % 90 == 0) 20f else 10f
            val start = Offset(
                x = center.width + (radius - lineLength).toFloat() * cos(angleRad).toFloat(),
                y = center.height + (radius - lineLength).toFloat() * sin(angleRad).toFloat()
            )
            val end = Offset(
                x = center.width + radius * cos(angleRad).toFloat(),
                y = center.height + radius * sin(angleRad).toFloat()
            )

            drawLine(
                color = if (i % 90 == 0) Color.White else Color.Gray,
                start = start,
                end = end,
                strokeWidth = 2.dp.toPx()
            )
        }
    }

    // Letras cardinales sobre el Canvas
    Box(modifier = Modifier.size(250.dp)) {
        val labelStyle =
            androidx.compose.ui.text.TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("N", color = Color.Red, style = labelStyle, modifier = Modifier.align(Alignment.TopCenter).padding(top = 10.dp))
        Text("S", color = Color.White, style = labelStyle, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp))
        Text("E", color = Color.White, style = labelStyle, modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp))
        Text("O", color = Color.White, style = labelStyle, modifier = Modifier.align(Alignment.CenterStart).padding(start = 12.dp))
    }
}

@Composable
fun CompassNeedle(angle: Float) {
    Image(
        painter = painterResource(id = R.drawable.aguja),
        contentDescription = "Aguja de brújula",
        modifier = Modifier
            .size(160.dp)
            .graphicsLayer {
                rotationZ = angle
                transformOrigin = TransformOrigin(0.5f, 0.5f)
            }
    )
}

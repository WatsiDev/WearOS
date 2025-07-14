package com.watsidev.producto2.presentation.screens.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.ImagesearchRoller
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.ui.graphics.vector.ImageVector
import com.watsidev.producto2.presentation.navigation.Calculator
import com.watsidev.producto2.presentation.navigation.Designs
import com.watsidev.producto2.presentation.navigation.Game
import com.watsidev.producto2.presentation.navigation.HeartRate
import com.watsidev.producto2.presentation.navigation.IMC
import com.watsidev.producto2.presentation.navigation.MusicPlayer
import com.watsidev.producto2.presentation.navigation.Settings
import com.watsidev.producto2.presentation.navigation.StepCounter
import com.watsidev.producto2.presentation.navigation.Temporizer

data class Apps(
    val id: Int,
    val name: String,
    val icon: ImageVector,
    val route: Any
)

val listApps = listOf(
    Apps(
        1,
        "Calculator",
        Icons.Default.PlusOne,
        Calculator
    ),
    Apps(
        2,
        "Music Player",
        Icons.Default.PlayCircle,
        MusicPlayer
    ),
    Apps(
        3,
        "Settings",
        Icons.Default.Settings,
        Settings // Placeholder for settings route, replace with actual route if needed
    ),
    Apps(
        4,
        "Designs",
        Icons.Default.ImagesearchRoller, // Placeholder icon, replace with actual weather icon
        Designs // Placeholder for weather route, replace with actual route if needed
    ),
    Apps(
        5,
        "Pokémon Game",
        Icons.Default.Gamepad, // Placeholder icon, replace with actual game icon
        Game
    ),
    Apps(
        6,
        "Heart Rate",
        Icons.Default.Favorite, // Placeholder icon, replace with actual settings icon
        HeartRate // Placeholder for settings route, replace with actual route if needed
    ),
    Apps(
        7,
        "IMC",
        Icons.Default.MonitorWeight, // Placeholder icon, replace with actual settings icon
        IMC // Placeholder for settings route, replace with actual route if needed
    ),
    Apps(
        8,
        "Temporizer",
        Icons.Default.Timer, // Placeholder icon, replace with actual settings icon
        Temporizer // Placeholder for settings route, replace with actual route if needed
    ),
    Apps(
        9,
        "Step Counter",
        Icons.AutoMirrored.Filled.DirectionsWalk, // Placeholder icon, replace with actual settings icon
        StepCounter // Placeholder for settings route, replace with actual route if needed
    )
)
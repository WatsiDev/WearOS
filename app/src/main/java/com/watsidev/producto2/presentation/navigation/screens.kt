package com.watsidev.producto2.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Cover

@Serializable
object Menu

@Serializable
object MusicPlayer

@Serializable
object Calculator

@Serializable
object Settings

@Serializable
object Designs

@Serializable
object Designs2

@Serializable
object Designs3

@Serializable
object Designs4

@Serializable
object Designs5

@Serializable
object HeartRate

@Serializable
object IMC

@Serializable
object Temporizer

@Serializable
object StepCounter

@Serializable
object Game

@Serializable
data class Fight(
    val id: Int,
)

@Serializable
data class ResultBattle(
    val message: String,
    val id: Int
)

@Serializable
object Maps

@Serializable
object Compass

@Serializable
data class TaskForm(
    val id: Int?
)

@Serializable
object TaskListView
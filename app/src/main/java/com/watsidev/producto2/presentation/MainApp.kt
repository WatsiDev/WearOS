package com.watsidev.producto2.presentation

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.wear.compose.material.Scaffold
import com.watsidev.producto2.presentation.navigation.Calculator
import com.watsidev.producto2.presentation.navigation.Compass
import com.watsidev.producto2.presentation.navigation.Cover
import com.watsidev.producto2.presentation.navigation.Designs
import com.watsidev.producto2.presentation.navigation.Designs2
import com.watsidev.producto2.presentation.navigation.Designs3
import com.watsidev.producto2.presentation.navigation.Designs4
import com.watsidev.producto2.presentation.navigation.Designs5
import com.watsidev.producto2.presentation.navigation.Fight
import com.watsidev.producto2.presentation.navigation.Game
import com.watsidev.producto2.presentation.navigation.HeartRate
import com.watsidev.producto2.presentation.navigation.IMC
import com.watsidev.producto2.presentation.navigation.Maps
import com.watsidev.producto2.presentation.navigation.Menu
import com.watsidev.producto2.presentation.navigation.MusicPlayer
import com.watsidev.producto2.presentation.navigation.ResultBattle
import com.watsidev.producto2.presentation.navigation.Settings
import com.watsidev.producto2.presentation.navigation.StepCounter
import com.watsidev.producto2.presentation.navigation.TaskForm
import com.watsidev.producto2.presentation.navigation.TaskListView
import com.watsidev.producto2.presentation.navigation.Temporizer
import com.watsidev.producto2.presentation.screens.calculator.CalculatorScreen
import com.watsidev.producto2.presentation.screens.cover.CoverScreen
import com.watsidev.producto2.presentation.screens.designs.ActivitiesScreen
import com.watsidev.producto2.presentation.screens.designs.ClockStepsScreen
import com.watsidev.producto2.presentation.screens.designs.DesignsScreen
import com.watsidev.producto2.presentation.screens.designs.MessagesScreen
import com.watsidev.producto2.presentation.screens.designs.YogaScreen
import com.watsidev.producto2.presentation.screens.game.CombatScreen
import com.watsidev.producto2.presentation.screens.game.CombatViewModel
import com.watsidev.producto2.presentation.screens.game.PokemonList
import com.watsidev.producto2.presentation.screens.game.ResultScreen
import com.watsidev.producto2.presentation.screens.game.SeleccionPokemonScreen
import com.watsidev.producto2.presentation.screens.game.StatsViewModel
import com.watsidev.producto2.presentation.screens.heartRate.HeartRateScreen
import com.watsidev.producto2.presentation.screens.imc.IMCScreen
import com.watsidev.producto2.presentation.screens.maps.CompassApp
import com.watsidev.producto2.presentation.screens.maps.MapLibreScreen
import com.watsidev.producto2.presentation.screens.menu.MenuScreen
import com.watsidev.producto2.presentation.screens.menu.listApps
import com.watsidev.producto2.presentation.screens.musicPlayer.MusicPlayerScreen
import com.watsidev.producto2.presentation.screens.settings.SettingsScreen
import com.watsidev.producto2.presentation.screens.stepCounter.StepCounterScreen
import com.watsidev.producto2.presentation.screens.taskList.TaskListScreen
import com.watsidev.producto2.presentation.screens.taskList.TaskScreen
import com.watsidev.producto2.presentation.screens.temporizer.TemporizerScreen

@Composable
fun MainApp(
    app: Application
) {
    val navController = rememberNavController()
    val statsViewModel: StatsViewModel = viewModel()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Cover
        ) {
            composable<Cover> {
                CoverScreen(
                    onClick = { navController.navigate(Menu) }
                )
            }
            composable<Menu> {
                MenuScreen(
                    onNavigateClick = { route ->
                        navController.navigate(route)
                    },
                    listApps = listApps
                )
            }
            composable<MusicPlayer> {
                MusicPlayerScreen(
                    viewModel = viewModel(factory = ViewModelProvider.AndroidViewModelFactory.getInstance(app))
                )
            }
            composable<Calculator> {
                CalculatorScreen()
            }
            composable<Settings> {
                SettingsScreen()
            }
            composable<Maps> {
                MapLibreScreen{ navController.navigate(Compass) }
            }
            composable<Compass> {
                CompassApp()
            }
            composable<Designs> {
                DesignsScreen{ navController.navigate(Designs2) }
            }
            composable<Designs2> {
                ActivitiesScreen{ navController.navigate(Designs3) }
            }
            composable<Designs3> {
                YogaScreen{ navController.navigate(Designs4) }
            }
            composable<Designs4> {
                ClockStepsScreen{ navController.navigate(Designs5) }
            }
            composable<Designs5> {
                MessagesScreen{ navController.navigate(Designs) }
            }
            composable<HeartRate> {
                HeartRateScreen()
            }
            composable<IMC> {
                IMCScreen()
            }
            composable<Temporizer> {
                TemporizerScreen()
            }
            composable<StepCounter> {
                StepCounterScreen()
            }
            composable<Game> {
                val pokemonRandom = remember {
                    PokemonList.shuffled().take(3)
                }
                SeleccionPokemonScreen(
                    onPokemonConfirmado = { pokemon ->
                        navController.navigate(Fight(pokemon))
                    },
                    pokemonList = pokemonRandom
                )
            }
            composable<Fight> { backStackEntry ->
                val fight: Fight = backStackEntry.toRoute()
                CombatScreen(
                    idPokemon = fight.id,
                    onFinBatalla = { navController.navigate(Game) },
                    goResults = { message, id -> navController.navigate(ResultBattle(message = message, id = id)) {
                        popUpTo(Game) { inclusive= false }
                    } }
                )
            }
            composable<ResultBattle> { backStackEntry ->
                val resultBattle: ResultBattle = backStackEntry.toRoute()
                ResultScreen(
                    idPokemon = resultBattle.id,
                    viewModel = statsViewModel,
                    mensaje = resultBattle.message,
                    onReintentar = { id ->
                        navController.navigate(Fight(id)) {
                            popUpTo(Game) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    },
                    onSeleccionarOtro = {
                        navController.navigate(Game) {
                            popUpTo(Game) {
                                inclusive = false // ← Conserva Game, limpia lo demás
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<TaskForm> {
                val taskForm: TaskForm = it.toRoute()
                TaskScreen(
                    onTaskSaved = { navController.navigate(TaskListView) },
                    id = taskForm.id
                )
            }
            composable<TaskListView> {
                TaskListScreen(
                    onAddTask = { navController.navigate(TaskForm(id = null)) },
                    onEditTask = { id -> navController.navigate(TaskForm(id = id)) }
                )
            }
        }
    }
}
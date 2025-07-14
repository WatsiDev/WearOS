package com.watsidev.producto2.presentation

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.wear.compose.material.Scaffold
import com.watsidev.producto2.presentation.navigation.Calculator
import com.watsidev.producto2.presentation.navigation.Cover
import com.watsidev.producto2.presentation.navigation.Designs
import com.watsidev.producto2.presentation.navigation.Fight
import com.watsidev.producto2.presentation.navigation.Game
import com.watsidev.producto2.presentation.navigation.HeartRate
import com.watsidev.producto2.presentation.navigation.IMC
import com.watsidev.producto2.presentation.navigation.Menu
import com.watsidev.producto2.presentation.navigation.MusicPlayer
import com.watsidev.producto2.presentation.navigation.Settings
import com.watsidev.producto2.presentation.navigation.StepCounter
import com.watsidev.producto2.presentation.navigation.Temporizer
import com.watsidev.producto2.presentation.screens.calculator.CalculatorScreen
import com.watsidev.producto2.presentation.screens.cover.CoverScreen
import com.watsidev.producto2.presentation.screens.designs.DesignsScreen
import com.watsidev.producto2.presentation.screens.game.CombateScreen
import com.watsidev.producto2.presentation.screens.game.SeleccionPokemonScreen
import com.watsidev.producto2.presentation.screens.heartRate.HeartRateScreen
import com.watsidev.producto2.presentation.screens.imc.IMCScreen
import com.watsidev.producto2.presentation.screens.menu.MenuScreen
import com.watsidev.producto2.presentation.screens.menu.listApps
import com.watsidev.producto2.presentation.screens.musicPlayer.MusicPlayerScreen
import com.watsidev.producto2.presentation.screens.settings.SettingsScreen
import com.watsidev.producto2.presentation.screens.stepCounter.StepCounterScreen
import com.watsidev.producto2.presentation.screens.temporizer.TemporizerScreen

@Composable
fun MainApp(
    app: Application
) {
    val navController = rememberNavController()
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
            composable<Designs> {
                DesignsScreen()
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
                SeleccionPokemonScreen(
                    onPokemonConfirmado = { pokemon ->
                        navController.navigate(Fight(pokemon))
                    }
                )
            }
            composable<Fight> { backStackEntry ->
                val fight: Fight = backStackEntry.toRoute()
                CombateScreen(
                    idPokemon = fight.id,
                    onFinBatalla = { navController.navigate(Game) }
                )
            }
        }
    }
}
package com.watsidev.producto2.presentation.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DoubleArrow
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.watsidev.producto2.R

@Composable
fun ResultScreen(
    idPokemon: Int,
    mensaje: String,
    viewModel: StatsViewModel,
    onReintentar: (Int) -> Unit,
    onSeleccionarOtro: () -> Unit,
) {
    val stats by viewModel.stats.collectAsState()
    val myPokemon = PokemonList.firstOrNull { it.id == idPokemon } ?: return

    // Reproducir música de victoria si aplica
    if (mensaje == "¡Ganaste!") ReproducirMusica(R.raw.victory)

    // Actualizar estadísticas UNA VEZ al entrar
    LaunchedEffect(mensaje) {
        viewModel.actualizarStats(mensaje)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.result_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        ScalingLazyColumn(
            modifier = Modifier
                .background(Color.Gray.copy(alpha = 0.5f))
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = mensaje,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            item {
                Image(
                    painterResource(myPokemon.spriteFront),
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                )
            }

            item {
                IconButton(
                    onClick = {
                        if (mensaje == "¡Ganaste!") {
                            onReintentar(idPokemon)
                        } else {
                            onSeleccionarOtro()
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (mensaje == "¡Ganaste!") Icons.Outlined.DoubleArrow else Icons.Outlined.RestartAlt,
                        contentDescription = null
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total de juegos: ${stats.totalCombates}")
                    Text("Juegos ganados: ${stats.victorias}/${stats.totalCombates}")
                    Text("Puntos: ${stats.puntos}")
                }
            }
        }
    }
}

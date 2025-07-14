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
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.watsidev.producto2.R

@Composable
fun ResultScreen(
    idPokemon: Int,
    mensaje: String,
    onReintentar: (Int) -> Unit,
    onSeleccionarOtro: () -> Unit,
) {
    val myPokemon = PokemonList.firstOrNull { it.id == idPokemon } ?: return
    if (mensaje == "¡Ganaste!") ReproducirMusica(R.raw.victory)
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
                    mensaje
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
                    onClick =  {
                        if (mensaje == "¡Ganaste!") {
                            onReintentar(idPokemon)
                        } else {
                            onSeleccionarOtro()
                        }
                    }
                ) {
                    Icon(
                        if (mensaje == "¡Ganaste!") Icons.Outlined.DoubleArrow else Icons.Outlined.RestartAlt ,
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
                    Text(
                        "Total de juegos: 4"
                    )
                    Text(
                        "Juegos ganados: 4"
                    )
                    Text(
                        "Puntos: 450"
                    )
                }
            }
        }
    }
}

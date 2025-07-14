package com.watsidev.producto2.presentation.screens.game

import android.content.Context
import android.graphics.ImageDecoder
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.GifDecoder
import com.watsidev.producto2.R

@Composable
fun SeleccionPokemonScreen(
    onPokemonConfirmado: (Int) -> Unit
) {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    val pokemones = listOf(
        Pokemon(
            1,
            "Charmander",
            R.drawable.gengar
        ),
        Pokemon(2,
            "Squirtle",
            R.drawable.gengar
        ),
        Pokemon(3,
            "Bulbasaur",
            R.drawable.gengar
        )
    )

    var seleccionado by remember { mutableStateOf<Pokemon?>(null) }

    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto superior
        item {
            Text(
                text = "Selecciona tu Pokémon",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        // Sprites en fila
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                pokemones.forEach { pokemon ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { seleccionado = pokemon }
                    ) {
                        AsyncImage(
                            model = pokemon.imagenRes,
                            imageLoader = imageLoader,
                            contentDescription = pokemon.nombre,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }

        // Nombre del Pokémon seleccionado
        item {
            AnimatedVisibility(visible = seleccionado != null) {
                seleccionado?.let { pokemon ->
                    // ← SOLUCIÓN: Envolver en Column
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = pokemon.nombre,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        IconButton(
                            onClick = { onPokemonConfirmado(pokemon.id) }
                        ) {
                            Icon(
                                Icons.Outlined.PlayArrow,
                                contentDescription = "Iniciar juego",
                            )
                        }
                    }
                }
            }
        }
    }
}
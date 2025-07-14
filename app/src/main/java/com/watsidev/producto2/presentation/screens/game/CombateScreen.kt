package com.watsidev.producto2.presentation.screens.game

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.GifDecoder
import com.watsidev.producto2.R

@Composable
fun CombateScreen(
    idPokemon: Int,
    onFinBatalla: () -> Unit
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

    val miPokemon = when (idPokemon) {
        1 -> Pokemon(1, "Charmander", R.drawable.gengar)
        2 -> Pokemon(2, "Squirtle", R.drawable.gengar)
        3 -> Pokemon(3, "Bulbasaur", R.drawable.gengar)
        else -> Pokemon(0, "MissingNo", R.drawable.gengar)
    }

    val enemigo = Pokemon(99, "Pidgey", R.drawable.gengar)

    var state by remember { mutableStateOf(CombateState()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🟥 Enemigo
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = enemigo.nombre, fontWeight = FontWeight.Bold)
            BarraVida(vida = state.vidaEnemigo)
            Spacer(modifier = Modifier.height(4.dp))
            AsyncImage(
                model = enemigo.imagenRes,
                imageLoader = imageLoader,
                contentDescription = enemigo.nombre,
                modifier = Modifier.size(48.dp)
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 6.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )

        // 🟦 Propio Pokémon
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = miPokemon.imagenRes,
                imageLoader = imageLoader,
                contentDescription = miPokemon.nombre,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            BarraVida(vida = state.vidaPropia)
            Text(text = miPokemon.nombre, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(4.dp))

        // 🎯 Acciones
        state.mensajeFinal?.let { mensaje ->
            Text(text = mensaje, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = { onFinBatalla() }) {
                Text("Volver")
            }
        } ?: if (state.mostrarOpciones) {
            Column {
                listOf("Ataque Rápido", "Lanzallamas", "Gruñido", "Placaje").forEach { ataque ->
                    Button(
                        onClick = {
                            // Simula golpe
                            val nuevaVidaEnemigo = (state.vidaEnemigo - 0.3f).coerceAtLeast(0f)
                            val nuevaVidaPropia = (state.vidaPropia - 0.2f).coerceAtLeast(0f)

                            val fin = when {
                                nuevaVidaEnemigo == 0f -> "¡Ganaste!"
                                nuevaVidaPropia == 0f -> "¡Perdiste!"
                                else -> null
                            }

                            state = state.copy(
                                vidaEnemigo = nuevaVidaEnemigo,
                                vidaPropia = nuevaVidaPropia,
                                mostrarOpciones = false,
                                mensajeFinal = fin
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                    ) {
                        Text(ataque)
                    }
                }
            }
        } else {
            Button(onClick = { state = state.copy(mostrarOpciones = true) }) {
                Text("Acción")
            }
        }
    }
}

package com.watsidev.producto2.presentation.screens.game

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.GifDecoder
import com.watsidev.producto2.R
import kotlinx.coroutines.delay
import okhttp3.internal.notifyAll

@Composable
fun CombatScreen(
    idPokemon: Int,
    onFinBatalla: () -> Unit,
    goResults: (String, Int) -> Unit,
    viewModel: CombatViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    ReproducirMusica(R.raw.battle_gym)
    LaunchedEffect(idPokemon) {
        viewModel.iniciarCombate(idPokemon)
    }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BackgroundCombat()

        Column {
            uiState.enemyPokemon?.let {
                EnemyPokemon(
                    enemyPokemon = it,
                    hpActual = uiState.enemyHp,
                    hpMax = uiState.enemyHpMax
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            uiState.myPokemon?.let {
                MyPokemon(
                    context = context,
                    myPokemon = it,
                    hpActual = uiState.myHp,
                    hpMax = uiState.myHpMax,
                    onMoveSelected = { move ->
                        viewModel.atacar(move)
                    }
                )
            }

            uiState.winnerMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Button(onClick = {
                    viewModel.reset()
                    onFinBatalla()
                }) {
                    Text("Volver")
                }
            }
            LaunchedEffect(uiState.winnerMessage) {
                uiState.winnerMessage?.let {
                    delay(2000) // tiempo para mostrar mensaje en la pantalla de combate
                    goResults(
                        uiState.winnerMessage!!,
                        uiState.myPokemon!!.id
                    )
                }
            }
        }
    }

}

@Composable
fun EnemyPokemon(
    enemyPokemon: Pokemon,
    hpActual: Int,
    hpMax: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        BarraVida(
            hpActual = hpActual,
            hpMax = hpMax,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            enemyPokemon.name,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Image(
            painterResource(enemyPokemon.spriteFront),
            contentDescription = enemyPokemon.name,
            modifier = Modifier
                .align(Alignment.End)
                .size(44.dp)
        )
    }
}


@Composable
fun MyPokemon(
    myPokemon: Pokemon,
    hpActual: Int,
    hpMax: Int,
    onMoveSelected: (String) -> Unit,
    context: Context
) {
    var showMoves by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painterResource(myPokemon.spriteBack),
                contentDescription = myPokemon.name,
                modifier = Modifier
                    .padding(start = 21.dp)
                    .size(64.dp)
            )

            BarraVida(
                hpActual = hpActual,
                hpMax = hpMax,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    myPokemon.name,
                    color = Color.Black
                )
                Button(
                    onClick = {
                        showMoves = !showMoves
                        reproducirEfecto(context, R.raw.select_sound)
                    },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.height(25.dp)
                ) {
                    Text("Acción", fontSize = 12.sp)
                }
            }
        }

        AnimatedVisibility(
            visible = showMoves,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AtaquesGrid(
                moves = myPokemon.moves,
                onMoveSelected = {
                    onMoveSelected(it)
                    showMoves = false
                },
                pokemonCry = myPokemon.cry,
                context = context
            )
        }
    }
}


@Composable
fun AtaquesGrid(
    moves: List<String>,
    onMoveSelected: (String) -> Unit,
    pokemonCry: Int,
    context: Context
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White.copy(alpha = 0.9f)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            for (i in moves.indices step 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (j in 0..1) {
                        if (i + j < moves.size) {
                            Button(
                                onClick = {
                                    onMoveSelected(moves[i + j])
                                    reproducirEfecto(context, pokemonCry)
                                },
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(25.dp)
                                    .weight(1f)
                            ) {
                                Text(moves[i + j], fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BackgroundCombat() {
    Image(
        painter = painterResource(R.drawable.combat_scenary_one),
        contentDescription = "Fondo de combate",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.combat_background),
            contentDescription = "Fondo de combate",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
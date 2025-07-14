package com.watsidev.producto2.presentation.screens.musicPlayer

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text

@Composable
fun MusicPlayerScreen(viewModel: MusicPlayerViewModel, context: Context = LocalContext.current) {
    val isPlaying by viewModel.isPlaying
    val currentMusic = viewModel.currentMusic

    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item {
            Image(
                painter = painterResource(id = currentMusic.coverImage),
                contentDescription = "Portada del álbum",
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(2.dp, Color.White, RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Text(
                text = currentMusic.name,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            Text(
                text = "${currentMusic.artist} • ${currentMusic.album}",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                IconButton(onClick = { viewModel.previous(context) }) {
                    Icon(
                        imageVector = Icons.Outlined.SkipPrevious,
                        contentDescription = "Anterior",
                        tint = Color.White
                    )
                }

                IconButton(
                    onClick = {
                        if (isPlaying) {
                            viewModel.pause()
                        } else {
                            viewModel.playCurrentSong(context)
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Outlined.Pause else Icons.Outlined.PlayArrow,
                        contentDescription = "Reproducir/Pausar",
                        tint = Color.White
                    )
                }

                IconButton(onClick = { viewModel.next(context) }) {
                    Icon(
                        imageVector = Icons.Outlined.SkipNext,
                        contentDescription = "Siguiente",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
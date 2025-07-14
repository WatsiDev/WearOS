package com.watsidev.producto2.presentation.screens.game

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun ReproducirMusica(@RawRes resId: Int) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, resId).apply {
            isLooping = true
        }
    }

    DisposableEffect(Unit) {
        mediaPlayer.start()
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}

fun reproducirEfecto(context: Context, @RawRes resId: Int) {
    val player = MediaPlayer.create(context, resId)
    player.setOnCompletionListener {
        it.release()
    }
    player.start()
}

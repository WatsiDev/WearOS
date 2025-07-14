package com.watsidev.producto2.presentation.screens.musicPlayer

import com.watsidev.producto2.R

data class Music(
    val id: Int,
    val name: String,
    val artist: String,
    val album: String,
    val duration: Int, // Duration in seconds
    val coverImage: Int, // Resource ID for the cover image
    val audioFile: Int // Resource ID for the audio file
)

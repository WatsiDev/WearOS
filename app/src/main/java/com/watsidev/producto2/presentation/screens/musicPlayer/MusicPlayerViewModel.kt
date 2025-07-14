package com.watsidev.producto2.presentation.screens.musicPlayer

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.State
import com.watsidev.producto2.R

class MusicPlayerViewModel(application: Application) : AndroidViewModel(application) {

    private var mediaPlayer: MediaPlayer? = null

    // Lista de canciones
    private val musicList = listOf(
        Music(
            id = 1,
            name = "Power of the Dollar",
            artist = "50 Cent",
            album = "Power of the Dollar",
            duration = 240,
            coverImage = R.drawable.power_of_the_dollar_cover,
            audioFile = R.raw.power_of_the_dollar
        ),
        Music(
            id = 2,
            name = "In Da Club",
            artist = "50 Cent",
            album = "Get Rich or Die Tryin'",
            duration = 260,
            coverImage = R.drawable.in_da_club_cover,
            audioFile = R.raw.in_da_club
        ),
        Music(
            id = 3,
            name = "Bad News",
            artist = "50 Cent",
            album = "Curtis",
            duration = 200,
            coverImage = R.drawable.curtis_cover,
            audioFile = R.raw.bad_news
        )
    )

    private val _currentIndex = mutableStateOf(0)
    val currentIndex: State<Int> = _currentIndex

    val currentMusic: Music
        get() = musicList[_currentIndex.value]

    private val _isPlaying = mutableStateOf(false)
    val isPlaying: State<Boolean> = _isPlaying

    fun playCurrentSong(context: Context) {
        stop()

        mediaPlayer = MediaPlayer.create(context, currentMusic.audioFile).apply {
            setOnCompletionListener {
                _isPlaying.value = false
                next(context) // Automatically play the next song when the current one finishes
            }
            start()
            _isPlaying.value = true
        }
    }

    fun pause() {
        mediaPlayer?.pause()
        _isPlaying.value = false
    }

    fun resume() {
        mediaPlayer?.start()
        _isPlaying.value = true
    }

    fun stop() {
        mediaPlayer?.release()
        mediaPlayer = null
        _isPlaying.value = false
    }

    fun next(context: Context) {
        _currentIndex.value = (_currentIndex.value + 1) % musicList.size
        playCurrentSong(context)
    }

    fun previous(context: Context) {
        _currentIndex.value =
            if (_currentIndex.value > 0) _currentIndex.value - 1 else musicList.lastIndex
        playCurrentSong(context)
    }

    override fun onCleared() {
        super.onCleared()
        stop()
    }
}

package com.example.musicplayer.ui.screens

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicplayer.data.models.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {

    private val _song = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _song
    private var player: ExoPlayer? = null
    var isPlaying = mutableStateOf(false)
    var currentPosition = mutableLongStateOf(0L)
      private var currentSongIndex = 0
    private val _currentSong = MutableStateFlow<Song?>(null)
    val currentSong : StateFlow<Song?> = _currentSong

    fun initializePlayer(context: Context) {
        player = ExoPlayer.Builder(context).build()
    }

    fun playAudio(audioPath: String) {

        player?.apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(audioPath)))
            prepare()
            play()
        }
        isPlaying.value = true

    }

    fun pauseAudio() {
        player?.pause()
        isPlaying.value = false
    }

    fun stopAudio() {
        player?.stop()
        player?.release()
        player = null
        isPlaying.value = false
    }

    override fun onCleared() {
        super.onCleared()
        player?.release()
    }

    fun seekTo(position: Long) {
        player?.seekTo(position)
        currentPosition.value = position


    }


    fun nextSong(){
        if (_song.value.isNotEmpty()){
            currentSongIndex = (currentSongIndex + 1) % _song.value.size
            _currentSong.value = _song.value[currentSongIndex]
            playAudio(_song.value[currentSongIndex].data ?: "")

        }

    }
    fun previousSong(){
        if (_song.value.isNotEmpty()){
            currentSongIndex = (currentSongIndex - 1) % _song.value.size
            _currentSong.value = _song.value[currentSongIndex]
            playAudio(_song.value[currentSongIndex].data ?: "")

        }
    }

    fun fetchSongs(context: Context) = viewModelScope.launch {

        val songList = mutableListOf<Song>()
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0 "


        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val imageColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)


            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val album = it.getLong(imageColumn)
                val title = it.getString(titleColumn)
                val artist = it.getString(artistColumn)
                val duration = it.getLong(durationColumn)
                val path = it.getString(dataColumn)

                val albumArtUri = "content://media/external/audio/albumart/$album"
                songList.add(Song(id, albumArtUri, title, artist, duration, path))
            }

            _song.value = songList
                                   
        }
    }
}

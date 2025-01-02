package com.example.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.musicplayer.ui.MusicPlayerApp
import com.example.musicplayer.ui.screens.MusicViewModel
import com.example.musicplayer.ui.theme.MusicPlayerTheme

class MainActivity : ComponentActivity() {
    private val musicViewModel : MusicViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MusicPlayerTheme {
                MusicPlayerApp(musicViewModel)
            }
        }


    }
}


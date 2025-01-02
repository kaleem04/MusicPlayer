package com.example.musicplayer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.musicplayer.R
import com.example.musicplayer.data.Utils
import com.example.musicplayer.data.models.Song


@Composable
fun BottomSheetContent(
    song: Song,
    musicViewModel: MusicViewModel,
    modifier: Modifier = Modifier
) {
    var slider by remember { mutableFloatStateOf(0f) }
    val isPlaying by musicViewModel.isPlaying

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null
                )
            }

            Text(
                text = stringResource(R.string.cover),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.5f)

            )

            IconButton(
                onClick = {},

                ) {
                Icon(
                    Icons.Outlined.MoreVert,
                    contentDescription = null
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp)

            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()

                ) {

                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(150.dp)

                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = song.title ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )

            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Outlined.FavoriteBorder,
                    contentDescription = null
                )
            }
        }

        Text(
            text = song.artist ?: "",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Text(
                text = "00"
            )

            Slider(
                value = slider,
                onValueChange = {
                    slider = it
                },
                valueRange = 0f..(song.duration?.toFloat() ?: 0f),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            )

            Text(
                text = "${song.duration?.let { Utils.calculateDuration(it) }}",
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            IconButton(
                onClick = {
                    musicViewModel.previousSong()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.previous),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }

            IconButton(
                onClick = {
                    if (isPlaying) {
                        musicViewModel.pauseAudio()
                    } else {
                        musicViewModel.playAudio(song.data ?: "")
                    }
                }
            ) {
                Icon(
                    if (isPlaying) painterResource(R.drawable.pause_btn) else painterResource(R.drawable.play_btn),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }

            IconButton(
                onClick = {
                    musicViewModel.nextSong()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.next),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }
        }
    }
}



package com.example.musicplayer.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicplayer.R
import com.example.musicplayer.data.Constants
import com.example.musicplayer.data.Utils
import com.example.musicplayer.data.models.Song


@Composable
fun MusicHomeScreen(
    musicViewModel: MusicViewModel,
    onSelectedSong: (Song) -> Unit,
    modifier: Modifier = Modifier
) {

    Column {
        MusicCategoryScroll()
        MusicScanRow()
        MusicRowTab(musicViewModel, onSelectedSong = onSelectedSong,
            onPlaySong = { audioPath ->
                musicViewModel.playAudio(audioPath)
            }

        )
    }
}

@Composable
fun MusicCategoryScroll(
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,


    ) {

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = contentPaddingValues,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(5.dp)

    ) {
        items(Constants.categories) { categories ->
            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = categories,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }


}

@Composable
fun MusicScanRow(
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 8.dp)


    ) {
        Text(
            text = stringResource(R.string.all_songs),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = stringResource(R.string.import_songs),
            style = MaterialTheme.typography.bodyLarge
        )

        IconButton(
            onClick = {}
        ) {
            Icon(
                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null
            )
        }

    }
}

@Composable
fun MusicRowTab(
    musicViewModel: MusicViewModel,
    onSelectedSong: (Song) -> Unit,
    onPlaySong: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTab = remember { mutableIntStateOf(0) }

    TabRow(
        divider = {},
        selectedTabIndex = selectedTab.intValue,
        modifier = Modifier
            .padding(horizontal = 10.dp)


    ) {
        Constants.tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab.intValue == index,
                onClick = {
                    selectedTab.intValue = index
                },

                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )

        }
    }

    when (selectedTab.intValue) {
        0 -> {
            MusicItems(
                musicViewModel,
                onSelectedSong = onSelectedSong,
                onPlaySong = onPlaySong

            )
        }

        1 -> {}
        2 -> {}
        3 -> {}
    }
}

@Composable
fun MusicItemList(
    song: Song,
    modifier: Modifier = Modifier
) {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(10.dp)
            .then(modifier)

    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)

            ) {

                Text(
                    text = song.title ?: "",
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .weight(1f)

                )


                Text(
                    text = "${song.duration?.let { Utils.calculateDuration(it) }}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier


                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()

            ) {

                Text(
                    text = song.artist ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.weight(1f)

                )

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        Icons.Outlined.MoreVert,
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Composable
fun MusicItems(
    viewModel: MusicViewModel = viewModel(),
    onSelectedSong: (Song) -> Unit,
    onPlaySong: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val songs by viewModel.songs.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchSongs(context)

    }
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onPlaySong }
            ) {
                Icon(
                    painter = painterResource(R.drawable.play_btn),
                    contentDescription = null
                )
            }

            Text(
                text = stringResource(R.string.shuffle),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Outlined.Menu,
                    contentDescription = null
                )
            }
        }


        LazyColumn {
            items(songs) { song ->
                MusicItemList(
                    song = song,
                    modifier = Modifier.clickable {
                        onSelectedSong(song)
                        onPlaySong(song.data ?: "")
                    }
                )
            }
        }
    }

}






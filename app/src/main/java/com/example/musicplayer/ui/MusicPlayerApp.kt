package com.example.musicplayer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicplayer.R
import com.example.musicplayer.data.models.Song
import com.example.musicplayer.ui.screens.BottomSheetContent
import com.example.musicplayer.ui.screens.MusicHomeScreen
import com.example.musicplayer.ui.screens.MusicViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerApp(
    musicViewModel: MusicViewModel,
    modifier: Modifier = Modifier
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var currentSong by remember { mutableStateOf<Song?>(null) }
    val isPlaying by musicViewModel.isPlaying
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        musicViewModel.initializePlayer(context)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = { MusicPlayerTopAppBar(scrollBehavior = topAppBarScrollBehavior) },
            bottomBar = {
                Spacer(modifier = Modifier.height(80.dp))

            },
            floatingActionButton = {

                ExtendedFloatingActionButton(
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        showBottomSheet = true
                    },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)


                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        Image(
                            painter = painterResource(R.drawable.music),
                            contentDescription = null,
                            modifier = Modifier
                                .size(height = 40.dp, width = 50.dp)
                                .clip(shape = RoundedCornerShape(8.dp))

                        )

                        currentSong?.title?.let {
                            Text(
                                text = it,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .weight(1f)
                                    .width(150.dp)
                                    .padding(8.dp)

                            )
                        }

                        IconButton(
                            onClick = {
                                if (isPlaying) {
                                    musicViewModel.pauseAudio()
                                } else {
                                    musicViewModel.playAudio(currentSong?.data ?: "")
                                }
                            }
                        ) {
                            Icon(
                                if (isPlaying) painterResource(R.drawable.pause_btn) else painterResource(
                                    R.drawable.play_btn
                                ),
                                contentDescription = null
                            )
                        }

                        IconButton(
                            onClick = {
                                musicViewModel.nextSong()
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.next),
                                contentDescription = null
                            )
                        }
                    }
                }


            },
            floatingActionButtonPosition = FabPosition.Center,
            modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)


        ) { contentPadding ->

            Column(
                modifier = Modifier.padding(contentPadding)
            ) {

                if (showBottomSheet) {

                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState
                    ) {
                        currentSong?.let { BottomSheetContent(it, musicViewModel) }
                    }
                }

                MusicHomeScreen(
                    musicViewModel,
                    onSelectedSong = { song ->
                        currentSong = song
                        showBottomSheet = true
                    },

                    )
            }

        }
        MusicBottomBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {

            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
                )
        },
        actions = {

            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Outlined.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

        },
        modifier = Modifier
            .padding(top = 8.dp)
            .then(modifier)


    )

}


@Composable
fun MusicBottomBar(
    modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null
                )
            }
        )
    }
}






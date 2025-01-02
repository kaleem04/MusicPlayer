package com.example.musicplayer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicplayer.R

@Composable
fun MusicScanScreen(modifier: Modifier = Modifier) {
    Column {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Import files from device"
                )
            }

            Image(
                painter = painterResource(R.drawable.music),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
                    .padding(bottom = 180.dp)
                    .size(120.dp)
            )

            Text(
                text = "Scan Audio Files",
                modifier = Modifier.align(Alignment.Center)

            )

            Text(
                text = "if you have songs downloaded that were not found" +
                        "by scanning . please save or move them to device storage - download or Music Folder",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
                    .padding(top = 80.dp, start = 16.dp, end = 16.dp)
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "Start Scanning",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScanPreview() {
    MusicScanScreen()
}
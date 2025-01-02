package com.example.musicplayer.ui.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayer.R


@Composable
fun AccountScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null
                )
            }

            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier.padding(vertical = 60.dp, horizontal = 10.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "profile pic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(60.dp)
                    )

                    Text(
                        text = "UserName"
                    )

                    Button(
                        onClick = {}
                    ) {
                        Text(
                            text = "Profile"
                        )
                    }
                }

            }
                Card (
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier.padding(top = 180.dp, start = 10.dp, end = 10.dp)

                ){
                    Column (
                        modifier = Modifier.padding(16.dp)
                    ){

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()

                        ) {
                            Icon(
                                Icons.Outlined.Settings,
                                contentDescription = null
                            )
                            Text(
                                text = "Settings",

                                )

                            Icon(
                                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                        Spacer(
                            modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()

                        ) {
                            Icon(
                                Icons.Outlined.Person,
                                contentDescription = null
                            )
                            Text(
                                text = "Help & Feedback",

                                )

                            Icon(
                                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                Icons.Outlined.Info,
                                contentDescription = null
                            )
                            Text(
                                text = "About",

                                )

                            Icon(
                                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                    }
                }



        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AccountPreview() {
    AccountScreen()
}
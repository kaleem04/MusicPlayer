package com.example.musicplayer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.musicplayer.R


// Set of Material typography styles to start with

val Funnel = FontFamily(
    Font(R.font.funnel_regular),
    Font(R.font.funnel_medium),
    Font(R.font.funnel_light),
    Font(R.font.funnel_bold)
)
val Typography = Typography(

    displayLarge = TextStyle(
        fontFamily = Funnel,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Funnel,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 18.sp,

    ),

    labelSmall = TextStyle(
        fontFamily = Funnel,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ) ,
    labelMedium = TextStyle(
        fontFamily = Funnel,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)
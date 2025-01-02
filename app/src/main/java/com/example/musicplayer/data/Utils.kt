package com.example.musicplayer.data


import java.util.Locale
import java.util.concurrent.TimeUnit


object Utils {

    fun calculateDuration(millis: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(millis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(hours)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(millis)
        )

        val formatedDuration =
            String.format(Locale.ENGLISH, "%2d:%02d",minutes, seconds)

        return formatedDuration
    }
}
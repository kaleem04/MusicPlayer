package com.example.musicplayer

import android.app.Application

class MusicApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }

    @Deprecated("Deprecated in Java")
    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }
}
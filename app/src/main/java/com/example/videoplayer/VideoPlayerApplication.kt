package com.example.videoplayer

import android.app.Application
import com.example.videoplayer.networking.RetrofitInstance
import com.example.videoplayer.networking.VideoApi
import com.example.videoplayer.repository.VideoListRepository
import com.example.videoplayer.storage.VideoDatabase

class VideoPlayerApplication : Application() {

    lateinit var videoListRepository: VideoListRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val videoApi = RetrofitInstance.getInstance().create(VideoApi::class.java)
        val videoDatabase = VideoDatabase.getDatabase(applicationContext)
        videoListRepository = VideoListRepository(videoApi, videoDatabase)
    }
}
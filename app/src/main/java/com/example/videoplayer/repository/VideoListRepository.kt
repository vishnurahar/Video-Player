package com.example.videoplayer.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.videoplayer.models.VideoResponse
import com.example.videoplayer.models.tables.VideoFileTable
import com.example.videoplayer.networking.VideoApi
import com.example.videoplayer.storage.VideoDatabase

class VideoListRepository(
    private val videoApi: VideoApi,
    private val videoDatabase: VideoDatabase
) {
    private val videoListLiveData = MutableLiveData<VideoResponse>()

    val videoList : LiveData<VideoResponse>
    get() = videoListLiveData

    suspend fun getVideosList() {
        val result = videoApi.getVideosList()
        if (result.body() != null){
            videoDatabase
                .videoDao()
                .addVideos(VideoFileTable(
                    result.body()!!.videos[0].id,
                    1)
                )
            videoListLiveData.postValue(result.body())
            Log.d("First call", "result ${result.body()}")
        }
    }
}
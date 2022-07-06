package com.example.videoplayer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.videoplayer.models.VideoResponse
import com.example.videoplayer.networking.VideoApi

class VideoListRepository(
    private val videoApi: VideoApi
) {
    private val videoListLiveData = MutableLiveData<VideoResponse>()

    val videoList : LiveData<VideoResponse>
    get() = videoListLiveData

    suspend fun getVideosList() {
        val result = videoApi.getVideosList()
        if (result.body() != null){
            videoListLiveData.postValue(result.body())
        }
    }
}
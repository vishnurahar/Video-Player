package com.example.videoplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.models.VideoResponse
import com.example.videoplayer.repository.VideoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: VideoListRepository
    ) : ViewModel() {

        init {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getVideosList()
            }
        }

    val videoList: LiveData<VideoResponse>
    get() = repository.videoList
}
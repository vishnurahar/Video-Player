package com.example.videoplayer.models

data class VideoResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val total_results: Int,
    val url: String,
    val videos: List<VideoItem>
)
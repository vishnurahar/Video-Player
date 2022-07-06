package com.example.videoplayer.networking

import com.example.videoplayer.models.VideoResponse
import com.example.videoplayer.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {

    @GET("popular")
    suspend fun getVideosList(
        @Query("per_page")
        videoPerPage: Int = 10
    ) : Response<VideoResponse>
}
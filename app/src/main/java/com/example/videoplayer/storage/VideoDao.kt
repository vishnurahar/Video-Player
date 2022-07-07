package com.example.videoplayer.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.videoplayer.models.tables.VideoFileTable

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVideos(vararg videos: VideoFileTable)

    @Query("SELECT * FROM videofile")
    fun getVideos() : List<VideoFileTable>
}
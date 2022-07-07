package com.example.videoplayer.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videofile")
data class VideoFileTable(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "view_count")
    val view_count: Int,
)
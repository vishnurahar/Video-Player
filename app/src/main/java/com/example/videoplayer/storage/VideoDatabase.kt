package com.example.videoplayer.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.videoplayer.models.tables.VideoFileTable

@Database(entities = [VideoFileTable::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {

    abstract fun videoDao() : VideoDao

    companion object{
        @Volatile
        private var INSTANCE: VideoDatabase? = null

        fun getDatabase(context: Context): VideoDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        VideoDatabase::class.java,
                        "videoDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}
package com.example.videoplayer.storage.typeconvertors

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.videoplayer.models.User
import com.example.videoplayer.models.VideoFile
import com.example.videoplayer.models.VideoPicture
import com.google.gson.Gson

@ProvidedTypeConverter
class VideoTypeConvertor(private val gson: Gson) {
    @TypeConverter
    fun toUser(userJson : String?) : User?{
        return gson.fromJson(userJson, User::class.java)
    }

    @TypeConverter
    fun fromUser(userJson : User) : String?{
        return gson.toJson(userJson)
    }

    @TypeConverter
    fun toVideoFile(videoFileJson : String?) : VideoFile?{
        return gson.fromJson(videoFileJson, VideoFile::class.java)
    }

    @TypeConverter
    fun fromVideoFile(videoFileJson : VideoFile) : String?{
        return gson.toJson(videoFileJson)
    }

    @TypeConverter
    fun toVideoPicture(videoPictureJson : String?) : VideoPicture?{
        return gson.fromJson(videoPictureJson, VideoPicture::class.java)
    }

    @TypeConverter
    fun fromVideoPicture(videoPictureJson : VideoPicture) : String?{
        return gson.toJson(videoPictureJson)
    }
}
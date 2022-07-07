package com.example.videoplayer.networking

import com.example.videoplayer.util.Constants.Companion.API_KEY
import com.example.videoplayer.util.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    fun getInstance(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", API_KEY).build()
                chain.proceed(request)
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
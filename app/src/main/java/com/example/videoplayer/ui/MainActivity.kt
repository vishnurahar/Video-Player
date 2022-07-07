package com.example.videoplayer.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.R
import com.example.videoplayer.VideoPlayerApplication
import com.example.videoplayer.adapters.VideoListAdapter
import com.example.videoplayer.databinding.ActivityMainBinding
import com.example.videoplayer.networking.RetrofitInstance
import com.example.videoplayer.networking.VideoApi
import com.example.videoplayer.repository.VideoListRepository
import com.example.videoplayer.viewmodel.MainViewModel
import com.example.videoplayer.viewmodel.MainViewModelFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private val videoListAdapter by lazy { VideoListAdapter{url ->
        val player = ExoPlayer.Builder(this).build()
        binding.ExoPlayerView.player = player
        val mediaItem = MediaItem.fromUri(url)
        player.addMediaItem(mediaItem)
        player.prepare()
        player.play()
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = (application as VideoPlayerApplication).videoListRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.videoList.observe(this, Observer {
            videoListAdapter.submitList(it.videos)
            Log.d("First call", "${it.total_results} and ${it.videos}")
        })

        binding.videoListRv.apply {
            val verticalLayout = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            layoutManager = verticalLayout
            adapter = videoListAdapter
        }

    }

//    override fun playVideo(url: String) {
//        val player = ExoPlayer.Builder(this).build()
//        binding.ExoPlayerView.player = player
//        val mediaItem = MediaItem.fromUri(url)
//        player.addMediaItem(mediaItem)
//        player.prepare()
//    }
}
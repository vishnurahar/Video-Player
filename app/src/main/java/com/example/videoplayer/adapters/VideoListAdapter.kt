package com.example.videoplayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.videoplayer.databinding.ListItemVideoListBinding
import com.example.videoplayer.models.VideoItem

class VideoListAdapter(private val onItemClickListener: (url: String) -> Unit) :
    ListAdapter<VideoItem, VideoListAdapter.VideoListViewHolder>(VIDEO_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): VideoListAdapter.VideoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemVideoListBinding.inflate(inflater, parent, false)
        return VideoListViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: VideoListAdapter.VideoListViewHolder,
        position: Int,
    ) {
        val videoListItem = getItem(position)
        videoListItem?.apply {
            holder.bind(this, onItemClickListener)
        }
    }


    inner class VideoListViewHolder(
        private val binding : ListItemVideoListBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(videoItem: VideoItem, onItemClickListener: (url: String) -> Unit){
            if (videoItem.image.isNotEmpty()){
                Glide.with(binding.videoThumbnailImage.context)
                    .load(videoItem.image)
                    .apply(RequestOptions.centerCropTransform())
                    .into(binding.videoThumbnailImage)
            }
            binding.titleTv.text = "Video ID: ${videoItem.id}"
            binding.subTitleTv.text = "User: ${videoItem.user.name}"

            binding.root.setOnClickListener {
                onItemClickListener.invoke(videoItem.video_files[0].link)
//                val player = ExoPlayer.Builder().build()
//                binding.root.ExoPlayerView.player = player
//                val mediaItem = MediaItem.fromUri(VIDEO_URL)
//                player.addMediaItem(mediaItem)
//                player.prepare()
            }
        }
    }

    companion object {
        private val VIDEO_COMPARATOR = object : DiffUtil.ItemCallback<VideoItem>() {
            override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean =
                oldItem == newItem
        }
    }
}


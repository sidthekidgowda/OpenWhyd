package com.openwhyd.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openwhyd.R
import com.openwhyd.databinding.HotTrackRowBinding
import com.openwhyd.handler.HotTrackHandler
import com.openwhyd.model.HotTrack
import org.apache.commons.lang3.StringUtils

class HotTracksAdapter(
    private val handler: HotTrackHandler,
    private val genre: String
) : ListAdapter<HotTrack, HotTracksAdapter.HotTracksViewHolder>(HotTracksDiffCallback()) {

    var hotTracks = listOf<HotTrack>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotTracksViewHolder {
        return HotTracksViewHolder(
            HotTrackRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return hotTracks.size
    }

    override fun onBindViewHolder(holder: HotTracksViewHolder, position: Int) {
        val hotTrack = hotTracks[position]
        val url = hotTrack.youtubeSrc?.getYoutubePath() ?: StringUtils.EMPTY

        holder.bind(
            hotTrack.img,
            hotTrack.name,
            genre,
            url,
            position,
            handler)
    }

    class HotTracksViewHolder(val binding: HotTrackRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(imgUrl: String?,
                 name: String,
                 genre: String,
                 youtubePath: String,
                 position: Int,
                 handler: HotTrackHandler) {

            binding.title = name
            binding.rowPosition = position
            binding.genre = genre
            binding.youtubePath = youtubePath
            binding.handler = handler

            Glide.with(binding.root.context)
                .load(imgUrl)
                .placeholder(R.drawable.ic_empty_image)
                .into(binding.artWork)

            binding.executePendingBindings()
        }
    }

    class HotTracksDiffCallback : DiffUtil.ItemCallback<HotTrack>() {

        override fun areItemsTheSame(oldItem: HotTrack, newItem: HotTrack): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HotTrack, newItem: HotTrack): Boolean {
            return oldItem == newItem
        }

    }
}
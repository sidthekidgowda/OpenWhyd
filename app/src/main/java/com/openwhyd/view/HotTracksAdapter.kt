package com.openwhyd.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openwhyd.R
import com.openwhyd.databinding.HotTrackRowBinding
import com.openwhyd.handler.HotTrackHandler
import com.openwhyd.model.HotTrackRes

class HotTracksAdapter(private val hotTrackRes: HotTrackRes,
                       private val handler: HotTrackHandler,
                       private val genre: String,
                       private val fragmentId: Int) : RecyclerView.Adapter<HotTracksAdapter.HotTracksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotTracksViewHolder {
        return HotTracksViewHolder(
            HotTrackRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return hotTrackRes.tracks.size
    }

    override fun onBindViewHolder(holder: HotTracksViewHolder, position: Int) {
        val hotTrack = hotTrackRes.tracks[position]
        holder.bind(hotTrack.img, hotTrack.name, genre, fragmentId, position, handler)
    }

    class HotTracksViewHolder(val binding: HotTrackRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(imgUrl: String?,
                 name: String,
                 genre: String,
                 fragmentId: Int,
                 position: Int,
                 handler: HotTrackHandler) {

            binding.title = name
            binding.rowPosition = position
            binding.genre = genre
            binding.fragmentId = fragmentId
            binding.handler = handler

            Glide.with(binding.root.context)
                .load(imgUrl)
                .placeholder(R.drawable.ic_empty_image)
                .into(binding.artWork)

            binding.executePendingBindings()
        }
    }
}
package com.openwhyd.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openwhyd.databinding.HotTrackRowBinding
import com.openwhyd.model.HotTrackRes

class HotTracksAdapter(val hotTrackRes: HotTrackRes) : RecyclerView.Adapter<HotTracksAdapter.HotTracksViewHolder>() {

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
        holder.bind(hotTrack.img, hotTrack.name)
    }

    class HotTracksViewHolder(val binding: HotTrackRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(img: String, name: String) {
            //bind image
            //bind text
            binding.title = name
        }
    }
}
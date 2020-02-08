package com.openwhyd.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openwhyd.databinding.HotTracksGenreRowBinding

class HotTracksGenreListAdapter(val hotTrackGenres: List<String>) : RecyclerView.Adapter<HotTracksGenreListAdapter.HotTrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotTrackViewHolder {
        return HotTrackViewHolder(HotTracksGenreRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return hotTrackGenres.size
    }

    override fun onBindViewHolder(holder: HotTrackViewHolder, position: Int) {
        holder.bind(hotTrackGenres[position])
    }

    class HotTrackViewHolder(private val binding: HotTracksGenreRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: String) {
            binding.genre = genre
            binding.executePendingBindings()
        }
    }
}
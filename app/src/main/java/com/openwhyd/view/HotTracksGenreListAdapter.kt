package com.openwhyd.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openwhyd.databinding.HotTracksGenreListRowBinding
import com.openwhyd.handler.HotTrackGenreHandler

class HotTracksGenreListAdapter(val hotTrackGenres: List<String>, val handler: HotTrackGenreHandler) : RecyclerView.Adapter<HotTracksGenreListAdapter.HotTrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotTrackViewHolder {
        return HotTrackViewHolder(HotTracksGenreListRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return hotTrackGenres.size
    }

    override fun onBindViewHolder(holder: HotTrackViewHolder, position: Int) {
        holder.bind(hotTrackGenres[position], handler)
    }

    class HotTrackViewHolder(private val binding: HotTracksGenreListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: String, handler: HotTrackGenreHandler) {
            binding.genre = genre
            binding.handler = handler
            binding.executePendingBindings()
        }
    }
}
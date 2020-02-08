package com.openwhyd.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HotTracksGenreAdapter(val hotTrackGenres: List<String>) : RecyclerView.Adapter<HotTracksGenreAdapter.HotTrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotTrackViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return hotTrackGenres.size
    }

    override fun onBindViewHolder(holder: HotTrackViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class HotTrackViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    }
}
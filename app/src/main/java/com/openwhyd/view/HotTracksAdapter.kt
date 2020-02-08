package com.openwhyd.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openwhyd.model.HotTrack

class HotTracksAdapter(val hotTracks: List<HotTrack>) : RecyclerView.Adapter<HotTracksAdapter.HotTracksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotTracksViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return hotTracks.size
    }

    override fun onBindViewHolder(holder: HotTracksViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class HotTracksViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }
}
package com.openwhyd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.openwhyd.R
import com.openwhyd.handler.HotTrackHandlerImpl
import kotlinx.android.synthetic.main.hot_tracks_genre_list.*

class HotTracksGenreListFragment : Fragment() {

    companion object {
        private val categories: List<String> = listOf("All", "Electro", "Hip hop",
            "Pop", "Indie", "Folk", "Rock", "Metal","Blues", "R&B", "Soul",
            "Jazz", "Classical", "Reggae", "Latin", "World")
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hot_tracks_genre_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HotTracksGenreListAdapter(categories, HotTrackHandlerImpl())
        hot_tracks_recycler_view.adapter = adapter
        hot_tracks_recycler_view.layoutManager = LinearLayoutManager(context)

    }
}
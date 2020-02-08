package com.openwhyd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.openwhyd.R
import com.openwhyd.databinding.HotTracksGenreListBinding
import com.openwhyd.handler.HotTrackGenreHandlerImpl

class HotTracksGenreListFragment : Fragment() {

    companion object {
        private val categories: List<String> = listOf("All", "Electro", "Hip hop",
            "Pop", "Indie", "Folk", "Rock", "Metal","Blues", "R&B", "Soul",
            "Jazz", "Classical", "Reggae", "Latin", "World")
    }

    private lateinit var binding: HotTracksGenreListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.hot_tracks_genre_list, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HotTracksGenreListAdapter(categories, HotTrackGenreHandlerImpl())
        binding.hotTracksRecyclerView.adapter = adapter
        binding.hotTracksRecyclerView.layoutManager = LinearLayoutManager(context)

    }
}
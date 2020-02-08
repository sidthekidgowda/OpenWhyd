package com.openwhyd.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.openwhyd.R
import com.openwhyd.model.HotTrackRes
import com.openwhyd.viewModel.HotTracksViewModelImpl
import kotlinx.android.synthetic.main.hot_tracks_list.*
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class HotTracksFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object Factory {
        const val EXTRA_TITLE = "title"

        fun createInstance(title: String): HotTracksFragment {
            val fragment = HotTracksFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_TITLE, title)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hot_tracks_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as HotTracksActivity).component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genre = arguments?.getString(EXTRA_TITLE) ?: StringUtils.EMPTY

        val hotTracksViewModel = ViewModelProvider(this, viewModelFactory).get(HotTracksViewModelImpl::class.java)
        hotTracksViewModel.getHotTracks(genre)

        val hotTracksObserver = Observer<HotTrackRes> { hotTrackRes ->
            //update recycler view
            val adapter = HotTracksAdapter(hotTrackRes)
            hot_tracks_recycler_view.adapter = adapter
            hot_tracks_recycler_view.layoutManager = LinearLayoutManager(context)
        }
        hotTracksViewModel.getHotTracksLiveData().observe(viewLifecycleOwner, hotTracksObserver)

    }
}
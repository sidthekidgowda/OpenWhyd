package com.openwhyd.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.openwhyd.R
import com.openwhyd.databinding.HotTrackDetailsBinding
import com.openwhyd.model.HotTrack
import com.openwhyd.viewModel.HotTracksViewModelImpl
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class HotTracksDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: HotTrackDetailsBinding

    companion object {
        const val EXTRA_GENRE = "genre"
        const val EXTRA_SELCTED_POSITION = "position"

        fun createInstance(genre: String, position: Int): HotTracksDetailsFragment {
            val fragment = HotTracksDetailsFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_GENRE, genre)
            bundle.putInt(EXTRA_SELCTED_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.hot_track_details, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //inject dependencies
        (activity as HotTracksDetailsActivity).component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genre = arguments?.getString(EXTRA_GENRE) ?: StringUtils.EMPTY
        val position = arguments?.getInt(EXTRA_SELCTED_POSITION) ?: 0

        val hotTracksViewModel = ViewModelProvider(this, viewModelFactory).get(HotTracksViewModelImpl::class.java)
        binding.viewModel = hotTracksViewModel

        hotTracksViewModel.getDetailsForHotTrack(genre, position)
        hotTracksViewModel.getHotTrackDetailsLiveData().observe(viewLifecycleOwner, Observer<Pair<String, HotTrack>>{ hotTrackPair ->
            binding.songTitle = hotTrackPair.second.name
            binding.user = "${hotTrackPair.second.user} ${getString(R.string.user_added_track)} ${hotTrackPair.first}"
            Glide.with(this)
                .load(hotTrackPair.second.img)
                .placeholder(R.drawable.ic_empty_image)
                .into(binding.hotTracksDetailsImage)
        })

    }

}
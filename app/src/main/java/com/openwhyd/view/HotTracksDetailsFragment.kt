package com.openwhyd.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.openwhyd.R
import com.openwhyd.model.HotTrack
import com.openwhyd.viewModel.HotTracksViewModelImpl
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class HotTracksDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
        return inflater.inflate(R.layout.hot_track_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //inject dependencies
        (activity as HotTracksActivity).component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genre = arguments?.getString(HotTracksDetailsFragment.EXTRA_GENRE) ?: StringUtils.EMPTY
        val position = arguments?.getInt(HotTracksDetailsFragment.EXTRA_SELCTED_POSITION) ?: 0

        val hotTracksViewModel = ViewModelProvider(this, viewModelFactory).get(HotTracksViewModelImpl::class.java)

        hotTracksViewModel.getDetailsForHotTrack(genre, position)
        hotTracksViewModel.getHotTrackDetailsLiveData().observe(viewLifecycleOwner, Observer<Pair<String, HotTrack>>{
            //set up title
            (activity as HotTracksActivity).setTitle(genre)
            //set image
            //set text
            //set
        })

    }

}
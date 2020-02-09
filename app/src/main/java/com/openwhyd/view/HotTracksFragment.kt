package com.openwhyd.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.openwhyd.R
import com.openwhyd.databinding.HotTracksListBinding
import com.openwhyd.handler.HotTrackHandlerImpl
import com.openwhyd.model.HotTrackRes
import com.openwhyd.viewModel.HotTracksViewModelImpl
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class HotTracksFragment : Fragment() {
    private var listCount: Int = 0

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding:HotTracksListBinding

    companion object {
        const val EXTRA_GENRE = "genre"

        fun createInstance(genre: String): HotTracksFragment {
            val fragment = HotTracksFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_GENRE, genre)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.hot_tracks_list, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as HotTracksActivity).component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genre = arguments?.getString(EXTRA_GENRE) ?: StringUtils.EMPTY

        (activity as HotTracksActivity).setTitle("${getString(R.string.hot_tracks)}: $genre")

        val hotTracksViewModel = ViewModelProvider(this, viewModelFactory).get(HotTracksViewModelImpl::class.java)
        binding.viewModel = hotTracksViewModel

        hotTracksViewModel.getHotTracks(genre)

        hotTracksViewModel.getHotTracksLiveData().observe(viewLifecycleOwner,  Observer<HotTrackRes> { hotTrackRes ->
            listCount = hotTrackRes.tracks.size
            val adapter = HotTracksAdapter(hotTrackRes, HotTrackHandlerImpl(), genre, id)
            binding.hotTracksRecyclerView.adapter = adapter
            binding.hotTracksRecyclerView.adapter = adapter
            binding.hotTracksRecyclerView.layoutManager = LinearLayoutManager(context)
        })

        binding.loadMoreContainer.setOnClickListener {loadContainer ->
            binding.loadMoreSpinner.visibility = View.VISIBLE
            loadContainer.isClickable = false
            loadContainer.isEnabled = false
            binding.loadMoreContainer.setBackgroundColor(
                ContextCompat.getColor((activity as HotTracksActivity), R.color.colorGrey))

            hotTracksViewModel.getMoreHotTracks(genre, listCount)
        }

        hotTracksViewModel.getMoreHotDetailsErrorLiveData().observe(viewLifecycleOwner, Observer<Boolean>{
            AlertDialog.Builder(context)
                .setMessage(getString(R.string.load_more_error))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok)) { dialog: DialogInterface, i: Int -> dialog.dismiss()}
                .create()
                .show()
        })

        hotTracksViewModel.resetLoadContainer().observe(viewLifecycleOwner, Observer<Boolean> {
            binding.loadMoreSpinner.visibility = View.INVISIBLE
            binding.loadMoreContainer.isClickable = true
            binding.loadMoreContainer.isEnabled = true
            binding.loadMoreContainer.setBackgroundColor(
                ContextCompat.getColor((activity as HotTracksActivity), R.color.colorPrimary))
        })

    }
}
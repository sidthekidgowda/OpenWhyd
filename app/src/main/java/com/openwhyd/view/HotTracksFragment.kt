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
import androidx.recyclerview.widget.GridLayoutManager
import com.openwhyd.R
import com.openwhyd.databinding.HotTracksListFragmentBinding
import com.openwhyd.handler.HotTrackHandler
import com.openwhyd.model.HotTrackRes
import com.openwhyd.viewModel.HotTracksViewModelImpl
import javax.inject.Inject

class HotTracksFragment : Fragment() {
    private var listCount: Int = 0

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var hotTrackHandler: HotTrackHandler

    private lateinit var binding: HotTracksListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.hot_tracks_list_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as HotTracksGenreListActivity).activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genre = HotTracksFragmentArgs.fromBundle(arguments!!).genre

        (activity as HotTracksGenreListActivity).setTitle("${getString(R.string.hot_tracks)}: $genre")

        val hotTracksViewModel = ViewModelProvider(this, viewModelFactory).get(HotTracksViewModelImpl::class.java)
        binding.viewModel = hotTracksViewModel

        val hotTracksAdapter = HotTracksAdapter(hotTrackHandler, genre)
        binding.hotTracksRecyclerView.adapter = hotTracksAdapter
//        binding.hotTracksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.hotTracksRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)


        //make service call
        hotTracksViewModel.getHotTracks(genre)

        hotTracksViewModel.getHotTracksLiveData().observe(viewLifecycleOwner,  Observer<HotTrackRes> { hotTrackRes ->
            listCount = hotTrackRes.tracks.size
            hotTracksAdapter.submitList(hotTrackRes.tracks)
        })

        binding.loadMoreContainer.setOnClickListener {loadContainer ->
            binding.loadMoreSpinner.visibility = View.VISIBLE
            loadContainer.isClickable = false
            loadContainer.isEnabled = false
            binding.loadMoreContainer.setBackgroundColor(
                ContextCompat.getColor(activity as HotTracksGenreListActivity, R.color.colorGrey))

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
                ContextCompat.getColor((activity as HotTracksGenreListActivity), R.color.colorPrimary))
        })

    }
}
package com.openwhyd.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.openwhyd.R
import com.openwhyd.viewModel.HotTracksViewModelImpl
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

        val hotTracksViewModel = ViewModelProvider(this, viewModelFactory).get(HotTracksViewModelImpl::class.java)

    }
}
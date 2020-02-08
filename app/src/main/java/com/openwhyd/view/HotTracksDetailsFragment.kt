package com.openwhyd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HotTracksDetailsFragment : Fragment() {

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_GENRE = "genre"
        const val EXTRA_SELCTED_POSITION = "position"

        fun createInstance(title: String, genre: String, position: Int): HotTracksDetailsFragment {
            val fragment = HotTracksDetailsFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_TITLE, title)
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
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
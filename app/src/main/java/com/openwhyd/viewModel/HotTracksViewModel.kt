package com.openwhyd.viewModel

import androidx.lifecycle.LiveData
import com.openwhyd.model.HotTrackRes

interface HotTracksViewModel {

    fun getHotTracks(genre: String)
    fun getHotTracksLiveData(): LiveData<HotTrackRes>
}
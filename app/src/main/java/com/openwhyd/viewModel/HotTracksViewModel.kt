package com.openwhyd.viewModel

import androidx.lifecycle.LiveData
import com.openwhyd.model.HotTrack

interface HotTracksViewModel {

    fun getHotTracks(genre: String)
    fun getHotTracksLiveData(): LiveData<List<HotTrack>>
}
package com.openwhyd.viewModel

import androidx.lifecycle.LiveData
import com.openwhyd.model.HotTrack
import com.openwhyd.model.HotTrackRes

interface HotTracksViewModel {

    fun getHotTracks(genre: String)
    fun getHotTracksLiveData(): LiveData<HotTrackRes>
    fun getMoreHotTracks(genre: String, position: Int)
    fun getDetailsForHotTrack(genre: String, position: Int)
    fun getHotTrackDetailsLiveData(): LiveData<Pair<String, HotTrack>>

    //View Operations
    fun loadingSpinnerVisibility(): LiveData<Int>
    fun loadMoreContainerVisibility(): LiveData<Int>
    fun recyclerViewVisibility(): LiveData<Int>
    fun errorImgVisibility(): LiveData<Int>
    fun resetLoadContainer(): LiveData<Boolean>
    fun getMoreHotDetailsErrorLiveData(): LiveData<Boolean>
    fun hotTrackDetailsVisibility(): LiveData<Int>
}
package com.openwhyd.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.rxviewmodelutility.BaseRxViewModel
import com.android.rxviewmodelutility.SingleLiveEvent
import com.android.rxviewmodelutility.addUIScheduler
import com.openwhyd.datasource.HotTracksDataSource
import com.openwhyd.model.HotTrack
import com.openwhyd.model.HotTrackRes
import javax.inject.Inject

class HotTracksViewModelImpl @Inject constructor(
    private val hotTracksDataSource: HotTracksDataSource
) : BaseRxViewModel(), HotTracksViewModel {

    private val hotTracksLiveData = MutableLiveData<HotTrackRes>()
    private val hotTrackDetailsLiveData = MutableLiveData<Pair<String, HotTrack>>()

    //View Operations
    private val loadingSpinnerVisibilityLiveData = MutableLiveData<Int>()
    private val loadMoreContainerVisibilityLiveData = MutableLiveData<Int>()
    private val recycleViewVisibilityLiveData = MutableLiveData<Int>()
    private val errorImgVisibilityLiveData = MutableLiveData<Int>()
    private val resetLoadContainerLiveData = MutableLiveData<Boolean>()
    private val moreHotTrackDetailsErrorLiveData = SingleLiveEvent<Boolean>()
    private val hotTrackDetailsVisibilityLiveData = MutableLiveData<Int>()
    private val hotTrackDetailsErrorLiveData = MutableLiveData<Int>()

    override fun getHotTracks(genre: String) {
        addDisposable(hotTracksDataSource.getHotTracks(genre)
            .doOnSubscribe { setLoadingViewState() }
            .doFinally { loadingSpinnerVisibilityLiveData.postValue(View.GONE) }
            .addUIScheduler()
            .subscribe(
                { hotTracks ->
                    hotTracksLiveData.postValue(hotTracks)
                    setSuccessNetworkResponseState()
                },
                { error ->
                    Log.e("ERROR-GettingHotTracks", error.message)
                    setNetworkFailureErrorState()
                })
        )
    }

    override fun getHotTracksLiveData(): LiveData<HotTrackRes> {
       return hotTracksLiveData
    }

    override fun getMoreHotTracks(genre: String, position: Int) {
        addDisposable(hotTracksDataSource.getHotTracks(genre, position)
            .addUIScheduler()
            .doFinally{ resetLoadContainerLiveData.postValue(true) }
            .subscribe(
                { hotTracksRes ->
                    hotTracksLiveData.postValue(hotTracksRes)
                },
                { error ->
                    moreHotTrackDetailsErrorLiveData.postValue(true)
                    Log.e("ERROR-MoreHotTracks", error.message)
                })
        )
    }

    override fun getDetailsForHotTrack(genre: String, position: Int) {
        addDisposable(hotTracksDataSource.getTrackDetails(genre, position)
            .doOnSubscribe { errorImgVisibilityLiveData.postValue(View.GONE) }
            .addUIScheduler()
            .subscribe(
                { hotTrackPair ->
                    hotTrackDetailsLiveData.postValue(hotTrackPair)
                },
                { hotTrackError ->
                    handleNetworkErrorForHotTrackDetails()
                    Log.e("ERROR-HotTrackDetails", hotTrackError.message)
                }
            ))
    }

    override fun getHotTrackDetailsLiveData(): LiveData<Pair<String, HotTrack>> {
        return hotTrackDetailsLiveData
    }

    override fun loadingSpinnerVisibility(): LiveData<Int> {
        return loadingSpinnerVisibilityLiveData
    }

    override fun loadMoreContainerVisibility(): LiveData<Int> {
        return loadMoreContainerVisibilityLiveData
    }

    override fun recyclerViewVisibility(): LiveData<Int> {
        return recycleViewVisibilityLiveData
    }

    override fun errorImgVisibility(): LiveData<Int> {
        return errorImgVisibilityLiveData
    }

    override fun resetLoadContainer(): LiveData<Boolean> {
        return resetLoadContainerLiveData
    }

    override fun getMoreHotDetailsErrorLiveData(): LiveData<Boolean> {
        return moreHotTrackDetailsErrorLiveData
    }

    override fun hotTrackDetailsVisibility(): LiveData<Int> {
        return hotTrackDetailsVisibilityLiveData
    }

    private fun setLoadingViewState() {
        loadingSpinnerVisibilityLiveData.postValue(View.VISIBLE)
        recycleViewVisibilityLiveData.postValue(View.GONE)
        loadMoreContainerVisibilityLiveData.postValue(View.GONE)
        errorImgVisibilityLiveData.postValue(View.GONE)
    }

    private fun setSuccessNetworkResponseState() {
        recycleViewVisibilityLiveData.postValue(View.VISIBLE)
        loadMoreContainerVisibilityLiveData.postValue(View.VISIBLE)
    }

    private fun setNetworkFailureErrorState() {
        errorImgVisibilityLiveData.postValue(View.VISIBLE)
    }

    private fun handleNetworkErrorForHotTrackDetails() {
        hotTrackDetailsVisibilityLiveData.postValue(View.GONE)
        errorImgVisibilityLiveData.postValue(View.VISIBLE)
    }
}
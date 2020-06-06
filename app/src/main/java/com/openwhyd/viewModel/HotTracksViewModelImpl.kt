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

    override fun getHotTracks(genre: String) {
        setLoadingViewState()

        addDisposable(hotTracksDataSource.getHotTracks(genre)
            .addUIScheduler()
            .subscribe(
                { hotTracks ->
                    hotTracksLiveData.value = hotTracks
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
            .subscribe(
                { hotTracksRes ->
                    resetLoadContainerLiveData.value = true
                    hotTracksLiveData.value = hotTracksRes
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
                    hotTrackDetailsLiveData.value = hotTrackPair
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
        loadMoreContainerVisibilityLiveData.value = View.GONE
        loadingSpinnerVisibilityLiveData.value = View.VISIBLE
        recycleViewVisibilityLiveData.value = View.GONE
        errorImgVisibilityLiveData.value = View.GONE
    }

    private fun setSuccessNetworkResponseState() {
        loadingSpinnerVisibilityLiveData.value = View.GONE
        recycleViewVisibilityLiveData.value = View.VISIBLE
        loadMoreContainerVisibilityLiveData.value = View.VISIBLE
    }

    private fun setNetworkFailureErrorState() {
        errorImgVisibilityLiveData.value = View.VISIBLE
    }

    private fun handleNetworkErrorForHotTrackDetails() {
        hotTrackDetailsVisibilityLiveData.value = View.GONE
        errorImgVisibilityLiveData.value = View.VISIBLE
    }
}
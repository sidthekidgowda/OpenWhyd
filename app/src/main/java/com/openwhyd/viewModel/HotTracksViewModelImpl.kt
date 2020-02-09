package com.openwhyd.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openwhyd.datasource.HotTracksDataSource
import com.openwhyd.model.HotTrack
import com.openwhyd.model.HotTrackRes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HotTracksViewModelImpl @Inject constructor(private val hotTracksDataSource: HotTracksDataSource)
    : ViewModel(), HotTracksViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val hotTracksLiveData = MutableLiveData<HotTrackRes>()
    private val hotTrackDetailsLiveData = MutableLiveData<Pair<String, HotTrack>>()

    //View Operations
    private val loadingSpinnerVisibility = MutableLiveData<Int>()
    private val loadMoreContainerVisibility = MutableLiveData<Int>()
    private val resetLoadContainerLiveData = MutableLiveData<Boolean>()
    private val moreHotTrackDetailsErrorLiveData = SingleLiveEvent<Boolean>()

    override fun getHotTracks(genre: String) {
        compositeDisposable.add(
            hotTracksDataSource.getHotTracks(genre)
                .doOnSubscribe {
                    loadingSpinnerVisibility.postValue(View.VISIBLE)
                    loadMoreContainerVisibility.postValue(View.GONE)
                }
                .doFinally {
                    loadingSpinnerVisibility.postValue(View.GONE)
                    loadMoreContainerVisibility.postValue(View.VISIBLE)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { hotTracks -> hotTracksLiveData.postValue(hotTracks)},
                    { error -> Log.e("@@@@ERROR", error.message) }))
    }

    override fun getHotTracksLiveData(): LiveData<HotTrackRes> {
       return hotTracksLiveData
    }

    override fun getMoreHotTracks(genre: String, position: Int) {
        compositeDisposable.add(
            hotTracksDataSource.getHotTracks(genre, position)
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally{resetLoadContainerLiveData.postValue(true)}
                .subscribe(
                    {hotTracksRes ->
                        hotTracksLiveData.postValue(hotTracksRes)
                    },
                    {error ->
                        moreHotTrackDetailsErrorLiveData.postValue(true)
                        Log.e("ERROR More Hot Tracks", error.message)
                    }))
    }

    override fun getDetailsForHotTrack(genre: String, position: Int) {
        compositeDisposable.add(
            hotTracksDataSource.getTrackDetails(genre, position)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {hotTrackPair ->
                        hotTrackDetailsLiveData.postValue(hotTrackPair)
                    },
                    {hotTrackError ->
                        Log.e("@@@ERROR", hotTrackError.message)
                    })
        )
    }

    override fun getHotTrackDetailsLiveData(): LiveData<Pair<String, HotTrack>> {
        return hotTrackDetailsLiveData
    }

    override fun loadingSpinnerVisibility(): LiveData<Int> {
        return loadingSpinnerVisibility
    }

    override fun loadMoreContainerVisibility(): LiveData<Int> {
        return loadMoreContainerVisibility
    }

    override fun resetLoadContainer(): LiveData<Boolean> {
        return resetLoadContainerLiveData
    }

    override fun getMoreHotDetailsErrorLiveData(): LiveData<Boolean> {
        return moreHotTrackDetailsErrorLiveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
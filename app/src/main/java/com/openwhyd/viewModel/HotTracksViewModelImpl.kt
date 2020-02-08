package com.openwhyd.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openwhyd.datasource.HotTracksDataSource
import com.openwhyd.model.HotTrackRes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HotTracksViewModelImpl @Inject constructor(private val hotTracksDataSource: HotTracksDataSource)
    : ViewModel(), HotTracksViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val hotTracksLiveData = MutableLiveData<HotTrackRes>()

    //View Visibility
    private val loadingSpinnerVisibility = MutableLiveData<Int>()
    private val loadMoreButtonVisibility = MutableLiveData<Int>()

    override fun getHotTracks(genre: String) {
        compositeDisposable.add(
            hotTracksDataSource.getHotTracks(genre)
                .doOnSubscribe {
                    loadingSpinnerVisibility.postValue(View.VISIBLE)
                    loadMoreButtonVisibility.postValue(View.GONE)
                }
                .doFinally {
                    loadingSpinnerVisibility.postValue(View.GONE)
                    loadMoreButtonVisibility.postValue(View.VISIBLE)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { hotTracks -> hotTracksLiveData.postValue(hotTracks)},
                    { error -> Log.e("@@@@ERROR", error.message) }))
    }

    override fun getHotTracksLiveData(): LiveData<HotTrackRes> {
       return hotTracksLiveData
    }

    override fun loadingSpinnerVisibility(): LiveData<Int> {
        return loadingSpinnerVisibility
    }

    override fun loadMoreButtonVisibility(): LiveData<Int> {
        return loadMoreButtonVisibility
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
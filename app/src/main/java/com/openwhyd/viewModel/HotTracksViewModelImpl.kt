package com.openwhyd.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openwhyd.datasource.HotTracksDataSource
import com.openwhyd.model.HotTrack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HotTracksViewModelImpl @Inject constructor(private val hotTracksDataSource: HotTracksDataSource)
    : ViewModel(), HotTracksViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val hotTracksLiveData = MutableLiveData<List<HotTrack>>()

    override fun getHotTracks(genre: String) {
        compositeDisposable.add(
            hotTracksDataSource.getHotTracks(genre)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { hotTracks -> hotTracksLiveData.postValue(hotTracks)},
                    { error -> Log.e("@@@@ERROR", error.message) }))
    }

    override fun getHotTracksLiveData(): LiveData<List<HotTrack>> {
       return hotTracksLiveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
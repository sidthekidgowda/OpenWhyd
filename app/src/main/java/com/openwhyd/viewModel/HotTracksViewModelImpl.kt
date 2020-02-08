package com.openwhyd.viewModel

import androidx.lifecycle.ViewModel
import com.openwhyd.datasource.HotTracksDataSource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HotTracksViewModelImpl @Inject constructor(private val hotTracksDataSource: HotTracksDataSource)
    : ViewModel(), HotTracksViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun makeHotTracksRequest() {
        //set up data source
        //@TODO
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
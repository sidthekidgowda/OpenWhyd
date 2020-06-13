package com.openwhyd.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.rxviewmodelutility.ViewModelFactory
import com.android.rxviewmodelutility.ViewModelKey
import com.openwhyd.handler.HotTrackHandler
import com.openwhyd.handler.HotTrackHandlerImpl
import com.openwhyd.viewModel.HotTracksViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HotTracksViewModelImpl::class)
    abstract fun bindHotTracksViewModel(viewModel: HotTracksViewModelImpl): ViewModel

    @Binds
    abstract fun bindHotTrackHandler(hotTrackHandler: HotTrackHandlerImpl): HotTrackHandler
}
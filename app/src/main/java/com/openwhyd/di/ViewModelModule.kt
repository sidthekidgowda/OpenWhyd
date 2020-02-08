package com.openwhyd.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.openwhyd.viewModel.HotTracksViewModelImpl
import com.openwhyd.viewModel.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import kotlin.reflect.KClass

abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ViewModelKey(HotTracksViewModelImpl::class)
    abstract fun bindHotTracksViewModel(viewModel: HotTracksViewModelImpl): ViewModel

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}
package com.openwhyd.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val viewModelMaps: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var viewModelCreator: Provider<out ViewModel>? = viewModelMaps[modelClass]

        if (viewModelCreator == null) {
            for ((key, value) in viewModelMaps) {
                if (modelClass.isAssignableFrom(key)) {
                    viewModelCreator = value
                    break
                }
            }
        }
        if (viewModelCreator == null) {
            throw IllegalArgumentException("Unknown model class: $modelClass")
        }
        try {
            @Suppress("UNCHECK_CAST")
            return viewModelCreator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
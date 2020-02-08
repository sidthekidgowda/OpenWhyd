package com.openwhyd.di

import android.app.Application
import com.openwhyd.di.module.DataSourceModule
import com.openwhyd.di.module.NetworkModule
import com.openwhyd.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class, DataSourceModule::class])
interface OpenWhydComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): OpenWhydComponent
    }
}
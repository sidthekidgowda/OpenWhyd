package com.openwhyd.di

import android.app.Application
import com.openwhyd.di.module.ActivityModule
import com.openwhyd.di.module.DataSourceModule
import com.openwhyd.di.module.NetworkModule
import com.openwhyd.di.module.ViewModelModule
import com.openwhyd.di.subcomponent.ActivityComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class,
    DataSourceModule::class, ActivityModule::class])
interface OpenWhydComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): OpenWhydComponent
    }

    //handle to subcomponent
    fun activityComponent(): ActivityComponent.Factory
}
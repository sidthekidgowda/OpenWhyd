package com.openwhyd.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface OpenWhydComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): OpenWhydComponent
    }
}
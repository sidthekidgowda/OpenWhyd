package com.openwhyd.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component
interface OpenWhydComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): OpenWhydComponent
    }
}
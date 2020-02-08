package com.openwhyd.application

import androidx.multidex.MultiDexApplication
import com.openwhyd.di.DaggerOpenWhydComponent
import com.openwhyd.di.OpenWhydComponent

class OpenWhydApplication : MultiDexApplication() {

    val component: OpenWhydComponent by lazy {
        DaggerOpenWhydComponent.factory().create(this)
    }
}
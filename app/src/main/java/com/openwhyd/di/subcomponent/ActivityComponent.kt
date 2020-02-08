package com.openwhyd.di.subcomponent

import com.openwhyd.view.HotTracksActivity
import com.openwhyd.view.HotTracksFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    //activity/fragments to inject
    fun inject(activity: HotTracksActivity)
    fun inject(fragment: HotTracksFragment)
}
package com.openwhyd.di.subcomponent

import com.openwhyd.view.HotTracksDetailsFragment
import com.openwhyd.view.HotTracksFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    //places to inject
    fun inject(fragment: HotTracksFragment)
    fun inject(fragment: HotTracksDetailsFragment)
}
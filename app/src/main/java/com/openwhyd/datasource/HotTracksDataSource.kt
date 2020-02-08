package com.openwhyd.datasource

import com.openwhyd.model.HotTrackRes
import io.reactivex.Single

interface HotTracksDataSource {

    fun getHotTracks(): Single<HotTrackRes>
}
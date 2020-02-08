package com.openwhyd.datasource

import com.openwhyd.model.HotTrack
import io.reactivex.Single

interface HotTracksDataSource {

    fun getHotTracks(genre: String): Single<List<HotTrack>>
}
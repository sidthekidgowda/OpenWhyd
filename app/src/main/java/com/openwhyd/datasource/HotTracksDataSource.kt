package com.openwhyd.datasource

import com.openwhyd.model.HotTrack
import com.openwhyd.model.HotTrackRes
import io.reactivex.Single

interface HotTracksDataSource {
    fun getHotTracks(genre: String, skip: Int=0): Single<HotTrackRes>
    fun getTrackDetails(genre: String, position: Int): Single<Pair<String, HotTrack>>
}
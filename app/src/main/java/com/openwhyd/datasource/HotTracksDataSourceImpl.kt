package com.openwhyd.datasource

import com.openwhyd.model.HotTrackRes
import com.openwhyd.service.HotTrackService
import io.reactivex.Single
import javax.inject.Inject

class HotTracksDataSourceImpl @Inject constructor(private val hotTrackService: HotTrackService)
    : HotTracksDataSource {

    override fun getHotTracks(genre: String): Single<HotTrackRes> {
        return hotTrackService.getHotTracks(genre, 0, 15)
    }
}
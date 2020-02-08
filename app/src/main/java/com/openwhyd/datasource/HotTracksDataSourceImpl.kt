package com.openwhyd.datasource

import com.openwhyd.model.HotTrackRes
import com.openwhyd.service.HotTrackService
import io.reactivex.Single
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class HotTracksDataSourceImpl @Inject constructor(private val hotTrackService: HotTrackService)
    : HotTracksDataSource {

    override fun getHotTracks(genre: String): Single<HotTrackRes> {
        val formattedPath =
            genre.split(StringUtils.SPACE)
                .joinToString(separator = StringUtils.EMPTY) {it.toLowerCase()}
        return hotTrackService.getHotTracks(formattedPath, 0, 15)
    }
}
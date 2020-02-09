package com.openwhyd.datasource

import com.openwhyd.model.HotTrack
import com.openwhyd.model.HotTrackRes
import com.openwhyd.service.HotTrackService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotTracksDataSourceImpl @Inject constructor(private val hotTrackService: HotTrackService)
    : HotTracksDataSource {

    private val hotTrackMapCache = mutableMapOf<String, HotTrackRes>()

    override fun getHotTracks(genre: String, skip: Int): Single<HotTrackRes> {
        val formattedPath =
            genre.split(StringUtils.SPACE)
                .joinToString(separator = StringUtils.EMPTY) {it.toLowerCase()}

        //if key exists in cache return the value
        //only make service call if key does not exist
        // or load more is requested.

        val shouldRerieveFromCache = hotTrackMapCache.containsKey(genre) &&
                hotTrackMapCache[genre]?.tracks?.size != skip

        if (shouldRerieveFromCache) {
            return Single.just(hotTrackMapCache[genre]!!).subscribeOn(Schedulers.io())
        } else {
            return hotTrackService.getHotTracks(formattedPath, skip)
                .doOnSuccess{ hotTrackRes: HotTrackRes? ->  hotTrackMapCache.put(genre, hotTrackRes!!)}
        }
    }

    override fun getTrackDetails(genre: String, position: Int): Single<Pair<String, HotTrack>> {
        return Single.just(hotTrackMapCache[genre])
            .map { it.genre to it.tracks[position] }
            .subscribeOn(Schedulers.single())
    }
}
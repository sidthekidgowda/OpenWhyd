package com.openwhyd.datasource

import com.android.rxviewmodelutility.addUpstreamScheduler
import com.openwhyd.model.HotTrack
import com.openwhyd.model.HotTrackRes
import com.openwhyd.service.HotTrackService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotTracksDataSourceImpl @Inject constructor(
    private val hotTrackService: HotTrackService
) : HotTracksDataSource {

    internal val hotTrackMapCache = mutableMapOf<String, HotTrackRes>()

    override fun getHotTracks(genre: String, skip: Int): Single<HotTrackRes> {
        val formattedPath =
            genre.split(StringUtils.SPACE)
                .joinToString(separator = StringUtils.EMPTY) { it.toLowerCase() }

        //if key exists in cache return the value
        //only make service call if key does not exist
        // or load more hot tracks button is clicked.

        val shouldRerieveFromCache = hotTrackMapCache.containsKey(genre) && skip == 0

        if (shouldRerieveFromCache) {
            return Single.just(hotTrackMapCache[genre]!!).addUpstreamScheduler(Schedulers.single())
        } else {
            //update cache and return original list with new list if it exists
            return hotTrackService.getHotTracks(formattedPath, skip)
                .map { hotTrackRes -> updateCache(genre, hotTrackRes!!) }
        }
    }

    override fun getTrackDetails(genre: String, position: Int): Single<Pair<String, HotTrack>> {
        if (!hotTrackMapCache.containsKey(genre)) return Single.error(Throwable("Map<K,V> does not contain Key"))
        return Single.just(hotTrackMapCache[genre])
            .map { it.genre to it.tracks[position] }
            .addUpstreamScheduler(Schedulers.single())
    }

    private fun updateCache(genre:String, updateHotTrackRes: HotTrackRes): HotTrackRes {
        //check if cache exists
        val oldHotTrackList = hotTrackMapCache[genre]?.tracks

        if (oldHotTrackList != null) {
            val updateList = oldHotTrackList + updateHotTrackRes.tracks
            val newHotTrackRes = HotTrackRes(updateHotTrackRes.hasMore, updateHotTrackRes.genre, updateList)
            hotTrackMapCache.put(genre, newHotTrackRes)
        } else {
            hotTrackMapCache.put(genre, updateHotTrackRes)
        }
        return hotTrackMapCache[genre]!!
    }
}
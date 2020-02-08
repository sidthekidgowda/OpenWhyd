package com.openwhyd.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotTrackRes(val hasMore: HotTrackHasMore,
                       val genre: String,
                       val tracks: List<HotTrack>)
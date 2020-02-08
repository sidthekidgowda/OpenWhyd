package com.openwhyd.model

import com.squareup.moshi.Json

data class HotTrackRes(@field:Json(name = "hasMore") val hasMore: HotTrackHasMore,
                       @field:Json(name = "genre") val genre: String = "",
                       @field:Json(name = "tracks") val tracks: List<HotTrack>)
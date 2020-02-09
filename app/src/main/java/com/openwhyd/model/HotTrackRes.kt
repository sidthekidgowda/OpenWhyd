package com.openwhyd.model

data class HotTrackRes(val hasMore: HotTrackHasMore,
                       val genre: String,
                       val tracks: List<HotTrack>)
package com.openwhyd.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotTrackHasMore(val skip: Int)
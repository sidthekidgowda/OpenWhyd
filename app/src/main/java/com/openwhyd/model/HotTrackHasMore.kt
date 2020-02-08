package com.openwhyd.model

import com.squareup.moshi.Json

data class HotTrackHasMore(@field:Json(name = "skip") val skip: Int)
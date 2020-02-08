package com.openwhyd.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotTrack(val id: String,
                    val name: String,
                    val img: String)
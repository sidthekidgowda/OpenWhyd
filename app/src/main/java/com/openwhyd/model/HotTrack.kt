package com.openwhyd.model

import com.squareup.moshi.Json

data class HotTrack(@Json(name="_id") val id: String,
                    val name: String,
                    val img: String)
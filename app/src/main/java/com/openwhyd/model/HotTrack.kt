package com.openwhyd.model

import com.squareup.moshi.Json

data class HotTrack(@field:Json(name = "id") val id: String = "",
                    @field:Json(name = "name") val name: String = "",
                    @field:Json(name = "img") val img: String = "")
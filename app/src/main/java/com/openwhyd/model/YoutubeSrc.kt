package com.openwhyd.model

import com.squareup.moshi.Json

data class YoutubeSrc(@Json(name = "id") val youtubeLink: String,
                      val name: String) {
}
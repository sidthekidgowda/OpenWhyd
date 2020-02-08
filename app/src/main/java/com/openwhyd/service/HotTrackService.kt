package com.openwhyd.service

import com.openwhyd.model.HotTrackRes
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HotTrackService {

    @GET("/{genre}?format=json")
    fun getHotTracks(@Path("genre") genre: String): Single<HotTrackRes>

     companion object {
         const val BASE_URL = "https://openwhy.org/hot/"
     }
}
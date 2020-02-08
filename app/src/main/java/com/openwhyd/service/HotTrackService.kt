package com.openwhyd.service

import com.openwhyd.model.HotTrackRes
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HotTrackService {

    @GET("{genre}?format=json")
    fun getHotTracks(@Path("genre") genre: String,
                     @Query("skip") skip:Int,
                     @Query("limit") limit: Int): Single<HotTrackRes>

     companion object {
         const val BASE_URL = "https://openwhyd.org/hot/"
     }
}
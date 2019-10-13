package com.appetiser.testapp.data

import com.appetiser.testapp.data.model.TrackResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackService {

    @GET("search")
    fun getTracks(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): Single<TrackResponse>

}
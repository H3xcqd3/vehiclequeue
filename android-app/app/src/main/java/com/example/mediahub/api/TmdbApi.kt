package com.example.mediahub.api

import com.example.mediahub.model.TmdbTrendingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("trending/all/day")
    suspend fun getTrending(): TmdbTrendingResponse

    @GET("search/multi")
    suspend fun search(@Query("query") query: String): TmdbTrendingResponse
}

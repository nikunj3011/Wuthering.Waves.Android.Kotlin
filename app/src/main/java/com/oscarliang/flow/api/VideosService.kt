package com.oscarliang.flow.api

import com.oscarliang.flow.models.api.VideosSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VideosService {

    @GET("/")
    suspend fun getVideos(
        @Query("a") key: String = "s"
    ): VideosSearchResponse
}
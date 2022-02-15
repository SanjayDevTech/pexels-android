package com.pexels.android.network

import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.param.Size
import com.pexels.android.model.video.ListVideosResponse
import com.pexels.android.model.video.VideoResource
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VideosPexelsService {
    @GET("/videos/search")
    suspend fun searchForVideos(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("orientation") orientation: Orientation?,
        @Query("size") size: Size?,
        @Query("locale") locale: Locale?,
        @Query("page") page: Int?,
        @Query("per_page") perPage:Int?,
    ): ListVideosResponse

    @GET("/videos/popular")
    suspend fun popularVideos(
        @Header("Authorization") apiKey: String,
        @Query("min_width") minWidth: Int?,
        @Query("min_height") minHeight: Int?,
        @Query("min_duration") minDuration: Int?,
        @Query("max_duration") maxDuration: Int?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
    ): ListVideosResponse

    @GET("/videos/videos/{id}")
    suspend fun getVideo(
        @Header("Authorization") apiKey: String,
        @Query("id") id: Int,
    ): VideoResource
}
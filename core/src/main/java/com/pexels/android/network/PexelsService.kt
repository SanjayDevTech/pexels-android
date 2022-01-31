package com.pexels.android.network

import com.pexels.android.model.PhotoResource
import com.pexels.android.model.response.ListPhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsService {
    @GET("/v1/search")
    suspend fun searchByQuery(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("orientation") orientation: String?,
        @Query("size") size: String?,
        @Query("color") color: String?,
        @Query("locale") locale: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): ListPhotosResponse

    @GET("/v1/curated")
    suspend fun curated(
        @Header("Authorization") apiKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): ListPhotosResponse

    @GET("/v1/photos/{id}")
    suspend fun getPhoto(
        @Header("Authorization") apiKey: String,
        @Path("id") id: Int,
    ): PhotoResource
}
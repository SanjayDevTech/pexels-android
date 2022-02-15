package com.pexels.android.network

import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.photo.ListPhotosResponse
import com.pexels.android.model.photo.PhotoResource
import com.pexels.android.model.param.Size
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotosPexelsService {
    @GET("/v1/search")
    suspend fun searchForPhotos(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("orientation") orientation: Orientation?,
        @Query("size") size: Size?,
        @Query("color") color: String?,
        @Query("locale") locale: Locale?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
    ): ListPhotosResponse

    @GET("/v1/curated")
    suspend fun curatedPhotos(
        @Header("Authorization") apiKey: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
    ): ListPhotosResponse

    @GET("/v1/photos/{id}")
    suspend fun getPhoto(
        @Header("Authorization") apiKey: String,
        @Path("id") id: Int,
    ): PhotoResource
}
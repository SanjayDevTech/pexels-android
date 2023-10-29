package com.pexels.android.operation

import com.pexels.android.model.photo.PhotoResource
import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.param.Size
import com.pexels.android.model.photo.ListPhotosResponse
import com.pexels.android.model.video.ListVideosResponse
import com.pexels.android.model.video.VideoResource
import com.pexels.android.network.PexelsService

/**
 * The actual implementation of [PexelsOperation]
 *
 * @param apiKey
 * Get the apiKey from [API Key](https://www.pexels.com/api/new/)
 * @param pexelsService
 * Retrofit Interface that actually makes the API calls
 */
internal class PexelsOperationImpl(
    private val apiKey: String,
    private val pexelsService: PexelsService,
) : PexelsOperation {
    override suspend fun searchForPhotos(
        query: String,
        orientation: Orientation?,
        size: Size?,
        color: String?,
        locale: Locale?,
        page: Int?,
        perPage: Int?,
    ): ListPhotosResponse {
        return pexelsService.searchForPhotos(
            apiKey = apiKey,
            query = query,
            orientation = orientation,
            size = size,
            color = color,
            locale = locale,
            page = page,
            perPage = perPage,
        )
    }

    override suspend fun curatedPhotos(
        page: Int?,
        perPage: Int?,
    ): ListPhotosResponse {
        return pexelsService.curatedPhotos(
            apiKey = apiKey,
            page = page,
            perPage = perPage,
        )

    }

    override suspend fun getPhoto(
        id: Int,
    ): PhotoResource {
        return pexelsService.getPhoto(
            apiKey = apiKey,
            id = id,
        )
    }

    override suspend fun searchForVideos(
        query: String,
        orientation: Orientation?,
        size: Size?,
        color: String?,
        locale: Locale?,
        page: Int?,
        perPage: Int?,
    ): ListVideosResponse {
        return pexelsService.searchForVideos(
            apiKey = apiKey,
            query = query,
            orientation = orientation,
            size = size,
            locale = locale,
            page = page,
            perPage = perPage,
        )
    }

    override suspend fun popularVideos(
        minWidth: Int?,
        minHeight: Int?,
        minDuration: Int?,
        maxDuration: Int?,
        page: Int?,
        perPage: Int?,
    ): ListVideosResponse {
        return pexelsService.popularVideos(
            apiKey = apiKey,
            minWidth = minWidth,
            minHeight = minHeight,
            minDuration = minDuration,
            maxDuration = maxDuration,
            page = page,
            perPage = perPage,
        )
    }

    override suspend fun getVideo(
        id: Int,
    ): VideoResource {
        return pexelsService.getVideo(
            apiKey = apiKey,
            id = id,
        )
    }
}
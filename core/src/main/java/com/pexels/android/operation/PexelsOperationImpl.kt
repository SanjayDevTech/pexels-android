package com.pexels.android.operation

import com.pexels.android.model.PhotoResource
import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.param.Size
import com.pexels.android.model.response.ListPhotosResponse
import com.pexels.android.network.PexelsService

/**
 * The actual implementation of [PexelsOperation]
 *
 * @param apiKey
 * Get the apiKey from [API Key](https://www.pexels.com/api/new/)
 * @param pexelsService
 * Retrofit Interface that actually makes the API calls
 */
class PexelsOperationImpl(
    private val apiKey: String,
    private val pexelsService: PexelsService,
) : PexelsOperation {
    override suspend fun searchForPhotos(
        query: String,
        orientation: Orientation?,
        size: Size?,
        color: String?,
        locale: Locale?,
        page: Int,
        perPage: Int
    ): ListPhotosResponse {
        return pexelsService.searchByQuery(
            apiKey = apiKey,
            query = query,
            orientation = orientation?.displayName,
            size = size?.displayName,
            color = color,
            locale = locale?.displayName,
            page = page,
            perPage = perPage,
        )
    }

    override suspend fun curatedPhotos(
        page: Int,
        perPage: Int
    ): ListPhotosResponse {
        return pexelsService.curated(
            apiKey = apiKey,
            page = page,
            perPage = perPage,
        )

    }

    override suspend fun getPhoto(
        id: Int
    ): PhotoResource {
        return pexelsService.getPhoto(
            apiKey = apiKey,
            id = id,
        )
    }
}
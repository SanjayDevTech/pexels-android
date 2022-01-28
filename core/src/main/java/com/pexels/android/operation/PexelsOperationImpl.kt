package com.pexels.android.operation

import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.PhotoResource
import com.pexels.android.model.param.Size
import com.pexels.android.model.response.ListPhotosResponse

/**
 * The actual implementation of [PexelsOperation]
 */
class PexelsOperationImpl : PexelsOperation {
    override suspend fun searchForPhotos(
        apiKey: String,
        query: String,
        orientation: Orientation?,
        size: Size?,
        color: String?,
        locale: Locale?,
        page: Int,
        perPage: Int
    ): ListPhotosResponse {
        return ListPhotosResponse()
    }

    override suspend fun curatedPhotos(
        apiKey: String,
        page: Int,
        perPage: Int
    ): ListPhotosResponse {
        return ListPhotosResponse()
    }

    override suspend fun getPhoto(
        apiKey: String,
        id: Int
    ): PhotoResource {
        // TODO: Not implemented
        throw IllegalStateException("Not implemented")
    }
}
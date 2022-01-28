package com.pexels.android.operation

import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.PhotoResource
import com.pexels.android.model.param.Size
import com.pexels.android.model.response.ListPhotosResponse

/**
 * A interface that comprises all operations that can be made on Photos.
 * It should be inherited in [PexelsOperation]
 *
 * Making separate interface for each resource (Photo, Video, etc.) will make
 * development easier and prevents frustration while finding the right methods.
 */
interface PhotosOperation {
    suspend fun searchForPhotos(
        apiKey: String,
        query: String,
        orientation: Orientation? = null,
        size: Size? = null,
        color: String? = null,
        locale: Locale? = null,
        page: Int = 1,
        perPage: Int = 15,
    ): ListPhotosResponse

    suspend fun curatedPhotos(
        apiKey: String,
        page: Int = 1,
        perPage: Int = 15,
    ): ListPhotosResponse

    suspend fun getPhoto(
        apiKey: String,
        id: Int,
    ): PhotoResource
}
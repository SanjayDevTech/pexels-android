package com.pexels.android.operation

import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.param.Size
import com.pexels.android.model.photo.ListPhotosResponse
import com.pexels.android.model.photo.PhotoResource
import com.pexels.android.model.video.ListVideosResponse
import com.pexels.android.model.video.VideoResource

/**
 * A interface that comprises all operations that can be made on Videos.
 * It should be inherited in [PexelsOperation]
 *
 * Making separate interface for each resource (Photo, Video, etc.) will make
 * development easier and prevents frustration while finding the right methods.
 */
interface VideosOperation {
    suspend fun searchForVideos(
        query: String,
        orientation: Orientation? = null,
        size: Size? = null,
        color: String? = null,
        locale: Locale? = null,
        page: Int? = null,
        perPage: Int? = null,
    ): ListVideosResponse

    suspend fun popularVideos(
        minWidth: Int? = null,
        minHeight: Int? = null,
        minDuration: Int? = null,
        maxDuration: Int? = null,
        page: Int? = null,
        perPage: Int? = null,
    ): ListVideosResponse

    suspend fun getVideo(
        id: Int,
    ): VideoResource
}
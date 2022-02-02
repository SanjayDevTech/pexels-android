package com.pexels.android.operation

import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.param.Size
import com.pexels.android.model.photo.ListPhotosResponse
import com.pexels.android.model.photo.PhotoResource
import com.pexels.android.model.video.ListVideosResponse
import com.pexels.android.model.video.VideoResource

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
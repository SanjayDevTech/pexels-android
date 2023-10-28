package com.pexels.android.model.video

import com.squareup.moshi.Json

enum class VideoQuality {
    @Json(name = "hd")
    HD,
    @Json(name = "sd")
    SD,
}
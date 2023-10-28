package com.pexels.android.model.param

import com.squareup.moshi.Json


enum class Orientation {
    @Json(name = "landscape")
    LANDSCAPE,

    @Json(name = "portrait")
    PORTRAIT,

    @Json(name = "square")
    SQUARE,
}
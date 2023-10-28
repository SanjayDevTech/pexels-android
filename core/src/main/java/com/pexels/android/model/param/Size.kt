package com.pexels.android.model.param

import com.squareup.moshi.Json


enum class Size {
    @Json(name = "large")
    LARGE,

    @Json(name = "medium")
    MEDIUM,

    @Json(name = "small")
    SMALL,
}
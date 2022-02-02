package com.pexels.android.model.param

import com.google.gson.annotations.SerializedName

enum class Orientation {
    @SerializedName("landscape")
    LANDSCAPE,

    @SerializedName("portrait")
    PORTRAIT,

    @SerializedName("square")
    SQUARE,
}
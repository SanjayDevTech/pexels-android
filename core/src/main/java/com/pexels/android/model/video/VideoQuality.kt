package com.pexels.android.model.video

import com.google.gson.annotations.SerializedName

enum class VideoQuality {
    @SerializedName("hd")
    HD,
    @SerializedName("sd")
    SD,
}
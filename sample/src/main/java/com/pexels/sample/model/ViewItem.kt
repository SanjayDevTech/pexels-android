package com.pexels.sample.model

data class ViewItem(
    val id: Int,
    val url: String,
    val previewUrl: String,
    val color: String? = null,
)

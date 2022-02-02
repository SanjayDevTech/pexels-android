package com.pexels.android.model.video

/**
 * Preview picture of the [VideoResource].
 */
data class VideoPicture(

    /** The id of the `video_picture`. **/
    val id: Int,

    /** A link to the preview image. **/
    val picture: String,
    val nr: Int,
)

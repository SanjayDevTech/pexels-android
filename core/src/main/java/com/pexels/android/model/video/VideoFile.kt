package com.pexels.android.model.video

/**
 * Different size of [VideoResource]
 */
data class VideoFile(
    /** The id of the `video_file`. **/
    val id: Int,

    /** The video quality of the `video_file`. **/
    val quality: VideoQuality,

    /** The video format of the `video_file`. **/
    val fileType: String,

    /** The width of the `video_file` in pixels. **/
    val width: Int,

    /** The height of the `video_file` in pixels. **/
    val height: Int,

    /** A link to where the `video_file` is hosted. **/
    val link: String,
)

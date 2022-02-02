package com.pexels.android.model.video

import com.google.gson.annotations.SerializedName

/**
 * VideoResource
 *
 * Video data class to hold video from Pexels API
 */
data class VideoResource(
    /** The id of the video. **/
    val id: Int,

    /** The real width of the video in pixels. **/
    val width: Int,

    /** The real height of the video in pixels. **/
    val height: Int,

    /** The Pexels URL where the video is located. **/
    val url: String,

    /** URL to a screenshot of the video. **/
    val image: String,

    /** The duration of the video in seconds. **/
    val duration: Int,

    /** The videographer who shot the video. **/
    val user: Photographer,

    /** An array of different sized versions of the video. **/
    @SerializedName("video_files")
    val videoFiles: List<VideoFile>,

    /** An array of preview pictures of the video. **/
    @SerializedName("video_pictures")
    val videoPictures: List<VideoPicture>,
)

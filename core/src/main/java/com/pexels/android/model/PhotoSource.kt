package com.pexels.android.model

/**
 * PhotoSource
 *
 * src of the [PhotoResource]
 */
data class PhotoSource(
    /** The image without any size changes. It will be the same as the `width` and `height` attributes. **/
    val original: String,

    /** The image resized to `W 940px X H 650px` `DPR 2`. **/
    val large2x: String,

    /** The image resized to `W 940px X H 650px` `DPR 1`. **/
    val large: String,

    /** The image scaled proportionally so that it's new height is `350px`. **/
    val medium: String,

    /** The image scaled proportionally so that it's new height is `130px`. **/
    val small: String,

    /** The image cropped to `W 800px X H 1200px`. **/
    val portrait: String,

    /** The image cropped to `W 1200px X H 627px`. **/
    val landscape: String,

    /** The image cropped to `W 280px X H 200px`. **/
    val tiny: String,
)

package com.pexels.android.model.video

/**
 * Photographer
 */
data class Photographer(
    /** The id of the videographer. **/
    val id: Int,

    /** The name of the videographer. **/
    val name: String,

    /** The URL of the videographer's Pexels profile. **/
    val url: String,
)

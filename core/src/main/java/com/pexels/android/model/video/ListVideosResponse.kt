package com.pexels.android.model.video

import com.squareup.moshi.Json

/**
 * Response Object to hold data about a Pexels API List videos response
 */
data class ListVideosResponse(
    /** An list of [VideoResource] objects. **/
    val videos: List<VideoResource> = listOf(),

    /** The Pexels URL for the current search query. **/
    val url: String = "",

    /** The current page number. **/
    val page: Int = 0,

    /** The number of results returned with each page. **/
    @Json(name = "per_page")
    val perPage: Int = 15,

    /** The total number of results for the request. **/
    @Json(name = "total_results")
    val totalResults: Int = 0,

    /** URL for the previous page of results, if applicable. **/
    @Json(name = "prev_page")
    val prevPage: String? = null,

    /** URL for the next page of results, if applicable. **/
    @Json(name = "next_page")
    val nextPage: String? = null,
)

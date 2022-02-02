package com.pexels.android.model.video

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
    val perPage: Int = 15,

    /** The total number of results for the request. **/
    val totalResults: Int = 0,

    /** URL for the previous page of results, if applicable. **/
    val prevPage: String? = null,

    /** URL for the next page of results, if applicable. **/
    val nextPage: String? = null,
)

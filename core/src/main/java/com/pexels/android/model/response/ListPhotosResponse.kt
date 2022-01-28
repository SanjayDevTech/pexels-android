package com.pexels.android.model.response

import com.pexels.android.model.PhotoResource

/**
 * Response Object to hold data about a Pexels API List photos response
 */
data class ListPhotosResponse(
    /**
     * An list of [PhotoResource] objects.
     */
    val photos: List<PhotoResource> = listOf(),

    /**
     * The current page number.
     */
    val page: Int = 0,

    /**
     * The number of results returned with each page.
     */
    val perPage: Int = 15,

    /**
     * The total number of results for the request.
     */
    val totalResults: Int = 0,

    /**
     * URL for the previous page of results, if applicable.
     */
    val prevPage: String? = null,

    /**
     * URL for the next page of results, if applicable.
     */
    val nextPage: String? = null,
)

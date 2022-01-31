package com.pexels.android.model.response

import com.google.gson.annotations.SerializedName
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
    @SerializedName("per_page")
    val perPage: Int = 15,

    /**
     * The total number of results for the request.
     */
    @SerializedName("total_results")
    val totalResults: Int = 0,

    /**
     * URL for the previous page of results, if applicable.
     */
    @SerializedName("prev_page")
    val prevPage: String? = null,

    /**
     * URL for the next page of results, if applicable.
     */
    @SerializedName("next_page")
    val nextPage: String? = null,
)

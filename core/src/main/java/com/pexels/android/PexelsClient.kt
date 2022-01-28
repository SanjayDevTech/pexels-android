package com.pexels.android

import com.pexels.android.exception.PexelsResponseException
import com.pexels.android.model.*
import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.param.Size
import com.pexels.android.model.response.ListPhotosResponse
import com.pexels.android.model.task.OnCompleteListener
import com.pexels.android.model.task.OnFailureListener
import com.pexels.android.model.task.OnSuccessListener
import com.pexels.android.model.task.PexelsTask
import com.pexels.android.operation.PexelsOperation
import kotlinx.coroutines.*
import java.io.IOException
//import java.io.IOException
import kotlin.jvm.Throws

/**
 * Client Class for Pexels API
 * @param apiKey
 * Get the apiKey from [API Key](https://www.pexels.com/api/new/)
 * @param operation
 * Instance of [PexelsOperation] to make the testing easier (Dependency Injection).
 */
class PexelsClient (
    private val apiKey: String,
    private val operation: PexelsOperation,
) {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main) + job

    /**
     * Search for pexels photos with provided query string.
     *
     * This endpoint enables you to search Pexels for any topic that you would like.
     * For example your query could be something broad like `Nature`, `Tigers`, `People`.
     * Or it could be something specific like `Group of people working`.
     *
     * @param query
     * The search query. `Ocean`, `Tigers`, `Pears`, etc.
     * @param orientation
     * Desired photo orientation. The current supported orientations are:
     * [Orientation.LANDSCAPE], [Orientation.PORTRAIT] or [Orientation.SQUARE].
     * @param size
     * Minimum photo size. The current supported sizes are:
     * [Size.LARGE], [Size.MEDIUM] or [Size.SMALL].
     * @param color
     * Desired photo color.Supported colors:
     * [Color.RED], [Color.ORANGE], [Color.YELLOW], [Color.GREEN], [Color.TURQUOISE],
     * [Color.BLUE], [Color.VIOLET], [Color.PINK], [Color.BROWN], [Color.BLACK],
     * [Color.GRAY], [Color.WHITE]
     * or any hexadecimal color code (eg. `#ffffff`).
     * @param locale
     * The locale of the search you are performing. The current supported locales are:
     * [Locale.EN_US] [Locale.PT_BR] [Locale.ES_ES] [Locale.CA_ES] [Locale.DE_DE] [Locale.IT_IT]
     * [Locale.FR_FR] [Locale.SV_SE] [Locale.ID_ID] [Locale.PL_PL] [Locale.JA_JP] [Locale.ZH_TW]
     * [Locale.ZH_CN] [Locale.KO_KR] [Locale.TH_TH] [Locale.NL_NL] [Locale.HU_HU] [Locale.VI_VN]
     * [Locale.CS_CZ] [Locale.DA_DK] [Locale.FI_FI] [Locale.UK_UA] [Locale.EL_GR] [Locale.RO_RO]
     * [Locale.NB_NO] [Locale.SK_SK] [Locale.TR_TR] [Locale.RU_RU].
     * @param page
     * The page number you are requesting. `Default: 1`
     * @param perPage
     * The number of results you are requesting per page. `Default: 15` `Max: 80`
     *
     * @throws IllegalArgumentException
     * If any of the params were invalid
     * @throws Exception
     * If there is any errors from network
     * @throws PexelsResponseException
     * Response code other than 200
     *
     * @return [ListPhotosResponse]
     * Search response
     */
    @Throws(IllegalArgumentException::class, Exception::class, PexelsResponseException::class)
    suspend fun searchForPhotos(
        query: String,
        orientation: Orientation? = null,
        size: Size? = null,
        color: String? = null,
        locale: Locale? = null,
        page: Int = 1,
        perPage: Int = 15,
    ): ListPhotosResponse {
        validateQuery(query)
        validatePage(page)
        validatePerPage(perPage)
        return operation.searchForPhotos(
            apiKey,
            query, orientation, size, color, locale, page, perPage,
        )
    }

    @JvmOverloads
    fun searchForPhotosCallback(
        query: String,
        orientation: Orientation? = null,
        size: Size? = null,
        color: String? = null,
        locale: Locale? = null,
        page: Int = 1,
        perPage: Int = 15,
    ): PexelsTask<ListPhotosResponse> {
        var onCompleteListener: OnCompleteListener<ListPhotosResponse>? = null
        var onSuccessListener: OnSuccessListener<ListPhotosResponse>? = null
        var onFailureListener: OnFailureListener? = null
        val task = object : PexelsTask<ListPhotosResponse> {
            override fun setOnCompleteListener(cb: OnCompleteListener<ListPhotosResponse>) {
                onCompleteListener = cb
            }

            override fun setOnSuccessListener(cb: OnSuccessListener<ListPhotosResponse>) {
                onSuccessListener = cb
            }

            override fun setOnFailureListener(cb: OnFailureListener) {
                onFailureListener = cb
            }

            override fun cancel() {
                job.cancel()
            }

        }
        scope.launch {
            try {
                val result = searchForPhotos(
                    query, orientation, size, color, locale, page, perPage
                )
                onSuccessListener?.onSuccess(result)
                onCompleteListener?.onComplete(result, null)
            } catch (e: Exception) {
                onFailureListener?.onFailure(e)
                onCompleteListener?.onComplete(null, e)
            }
        }
        return task
    }

    /**
     * This endpoint enables you to receive real-time photos curated by the Pexels team.
     * We add at least one new photo per hour to our curated list so that you always get a
     * changing selection of trending photos.
     *
     * @param page
     * The page number you are requesting. `Default: 1`
     * @param perPage
     * The number of results you are requesting per page. `Default: 15` `Max: 80`
     *
     * @throws IllegalArgumentException
     * If any of the params were invalid
     * @throws Exception
     * If there is any errors from network
     * @throws PexelsResponseException
     * Response code other than 200
     *
     * @return [ListPhotosResponse]
     * Curated response
     */
    @Throws(IllegalArgumentException::class, Exception::class, PexelsResponseException::class)
    suspend fun curatedPhotos(
        page: Int = 1,
        perPage: Int = 15,
    ): ListPhotosResponse {
        validatePage(page)
        validatePerPage(perPage)
        return operation.curatedPhotos(
            apiKey,
            page, perPage,
        )
    }

    /**
     * Fetch a single Photo
     * Retrieve a specific [PhotoResource] from its id.
     *
     * @param id
     * The id of the photo you are requesting.
     *
     * @return [PhotoResource]
     */
    @Throws(IOException::class, PexelsResponseException::class)
    suspend fun getPhoto(
        id: Int,
    ): PhotoResource {
        return operation.getPhoto(
            apiKey,
            id,
        )
    }

    private fun validateQuery(query: String) {
        if (query.isBlank())
            throw IllegalArgumentException(
                "Query string is blank. Actual query we got: $query."
            )
    }

    private fun validatePage(page: Int) {
        if (page < 1)
            throw IllegalArgumentException(
                "page number should be greater than 0. Actual page number we got: $page"
            )
    }

    private fun validatePerPage(perPage: Int) {
        if (perPage !in 15 until 81)
            throw IllegalArgumentException(
                "perPage should be in range 15 to 80(inclusive). Actual perPage we got: $perPage"
            )
    }

}
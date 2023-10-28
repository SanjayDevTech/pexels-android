package com.pexels.android

import com.pexels.android.model.photo.PhotoResource
import com.pexels.android.model.param.Color
import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.param.Size
import com.pexels.android.model.photo.ListPhotosResponse
import com.pexels.android.model.task.OnCompleteListener
import com.pexels.android.model.task.OnFailureListener
import com.pexels.android.model.task.OnSuccessListener
import com.pexels.android.model.task.PexelsTask
import com.pexels.android.model.video.ListVideosResponse
import com.pexels.android.model.video.VideoResource
import com.pexels.android.operation.PexelsOperation
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.Objects

/**
 * Client Class for Pexels API
 * @param operation
 * Instance of [PexelsOperation] to make the testing easier (Dependency Injection).
 */
class PexelsClient (
    private val operation: PexelsOperation,
) {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main) + job

    // PHOTO STARTS
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
     * @throws HttpException
     * Response code other than 200
     *
     * @return [ListPhotosResponse]
     * Search response
     */
    @Throws(IllegalArgumentException::class, Exception::class, HttpException::class)
    suspend fun searchForPhotos(
        query: String,
        orientation: Orientation? = null,
        size: Size? = null,
        color: String? = null,
        locale: Locale? = null,
        page: Int? = null,
        perPage: Int? = null,
    ): ListPhotosResponse {
        validateQuery(query)
        validatePage(page)
        validatePerPage(perPage)
        return operation.searchForPhotos(
            query = query,
            orientation = orientation,
            size = size,
            color = color,
            locale = locale,
            page = page,
            perPage = perPage,
        )
    }

    /**
     * This is intended for Java callers.
     *
     * See [searchForPhotos] for documentation
     *
     * @param query Required
     * others are optional. null can be passed.
     */
    fun searchForPhotosCallback(
        query: String,
        orientation: Orientation?,
        size: Size?,
        color: String?,
        locale: Locale?,
        page: Int?,
        perPage: Int?,
    ): PexelsTask<ListPhotosResponse> {
        Objects.requireNonNull(query, "query should not be null")
        return executeCodeCallback {
            searchForPhotos(
                query = query,
                orientation = orientation,
                size = size,
                color = color,
                locale = locale,
                page = page,
                perPage = perPage,
            )
        }
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
     * @throws HttpException
     * Response code other than 200
     *
     * @return [ListPhotosResponse]
     * Curated response
     */
    @Throws(IllegalArgumentException::class, Exception::class, HttpException::class)
    suspend fun curatedPhotos(
        page: Int? = null,
        perPage: Int? = null,
    ): ListPhotosResponse {
        validatePage(page)
        validatePerPage(perPage)
        return operation.curatedPhotos(
            page = page,
            perPage = perPage,
        )
    }

    /**
     * This is intended for Java callers.
     *
     * See [curatedPhotos] for documentation
     *
     * all params are optional. null can be passed.
     */
    fun curatedPhotosCallback(
        page: Int?,
        perPage: Int?,
    ): PexelsTask<ListPhotosResponse> {
        return executeCodeCallback {
            curatedPhotos(
                page = page,
                perPage = perPage,
            )
        }
    }

    /**
     * Fetch a single Photo
     * Retrieve a specific [PhotoResource] from its id.
     *
     * @param id
     * The id of the photo you are requesting.
     *
     * @throws Exception
     * If there is any errors from network
     * @throws HttpException
     * Response code other than 200
     *
     * @return [PhotoResource]
     */
    @Throws(Exception::class, HttpException::class)
    suspend fun getPhoto(
        id: Int,
    ): PhotoResource {
        return operation.getPhoto(
            id = id,
        )
    }

    /**
     * This is intended for Java callers.
     *
     * See [getPhoto] for documentation
     *
     * @param id Required
     */
    fun getPhotoCallback(
        id: Int,
    ): PexelsTask<PhotoResource> {
        Objects.requireNonNull(id, "id should not be null")
        return executeCodeCallback {
            getPhoto(id = id)
        }
    }
    // PHOTO ENDS

    // VIDEO STARTS
    /**
     * Search for pexels videos with provided query string.
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
     * @throws HttpException
     * Response code other than 200
     *
     * @return [ListVideosResponse]
     * Search response
     */
    @Throws(IllegalArgumentException::class, Exception::class, HttpException::class)
    suspend fun searchForVideos(
        query: String,
        orientation: Orientation? = null,
        size: Size? = null,
        color: String? = null,
        locale: Locale? = null,
        page: Int? = null,
        perPage: Int? = null,
    ): ListVideosResponse {
        validateQuery(query)
        validatePage(page)
        validatePerPage(perPage)
        return operation.searchForVideos(
            query = query,
            orientation = orientation,
            size = size,
            color = color,
            locale = locale,
            page = page,
            perPage = perPage,
        )
    }

    /**
     * This is intended for Java callers.
     *
     * See [searchForVideos] for documentation
     *
     * @param query Required
     * others are optional. null can be passed.
     */
    fun searchForVideosCallback(
        query: String,
        orientation: Orientation?,
        size: Size?,
        color: String?,
        locale: Locale?,
        page: Int?,
        perPage: Int?,
    ): PexelsTask<ListVideosResponse> {
        Objects.requireNonNull(query, "query should not be null")
        return executeCodeCallback {
            searchForVideos(
                query = query,
                orientation = orientation,
                size = size,
                color = color,
                locale = locale,
                page = page,
                perPage = perPage,
            )
        }
    }

    /**
     * This endpoint enables you to receive the current popular Pexels videos.
     *
     * @param page
     * The page number you are requesting. `Default: 1`
     * @param perPage
     * The number of results you are requesting per page. `Default: 15` `Max: 80`
     * @param minWidth
     * The minimum width in pixels of the returned videos.
     * @param minHeight
     * The minimum height in pixels of the returned videos.
     * @param minDuration
     * The minimum duration in seconds of the returned videos.
     * @param maxDuration
     * The maximum duration in seconds of the returned videos.
     *
     * @throws IllegalArgumentException
     * If any of the params were invalid
     * @throws Exception
     * If there is any errors from network
     * @throws HttpException
     * Response code other than 200
     *
     * @return [ListVideosResponse]
     * Popular response
     */
    @Throws(IllegalArgumentException::class, Exception::class, HttpException::class)
    suspend fun popularVideos(
        minWidth: Int? = null,
        minHeight: Int? = null,
        minDuration: Int? = null,
        maxDuration: Int? = null,
        page: Int? = null,
        perPage: Int? = null,
    ): ListVideosResponse {
        validatePage(page)
        validatePerPage(perPage)
        return operation.popularVideos(
            minWidth = minWidth,
            minHeight = minHeight,
            minDuration = minDuration,
            maxDuration = maxDuration,
            page = page,
            perPage = perPage,
        )
    }

    /**
     * This is intended for Java callers.
     *
     * See [popularVideos] for documentation
     *
     * all params are optional. null can be passed.
     */
    fun popularVideosCallback(
        minWidth: Int?,
        minHeight: Int?,
        minDuration: Int?,
        maxDuration: Int?,
        page: Int?,
        perPage: Int?,
    ): PexelsTask<ListVideosResponse> {
        return executeCodeCallback {
            popularVideos(
                minWidth = minWidth,
                minHeight = minHeight,
                minDuration = minDuration,
                maxDuration = maxDuration,
                page = page,
                perPage = perPage,
            )
        }
    }

    /**
     * Fetch a single Video
     * Retrieve a specific [VideoResource] from its id.
     *
     * @param id
     * The id of the video you are requesting.
     *
     * @throws Exception
     * If there is any errors from network
     * @throws HttpException
     * Response code other than 200
     *
     * @return [VideoResource]
     */
    @Throws(Exception::class, HttpException::class)
    suspend fun getVideo(
        id: Int,
    ): VideoResource {
        return operation.getVideo(
            id = id,
        )
    }

    /**
     * This is intended for Java callers.
     *
     * See [getVideo] for documentation
     *
     * @param id Required
     */
    fun getVideoCallback(
        id: Int,
    ): PexelsTask<VideoResource> {
        Objects.requireNonNull(id, "id should not be null")
        return executeCodeCallback {
            getVideo(id = id)
        }
    }
    // VIDEO ENDS

    private fun <T> executeCodeCallback(code: suspend  () -> T): PexelsTask<T> {
        var onCompleteListener: OnCompleteListener<T>? = null
        var onSuccessListener: OnSuccessListener<T>? = null
        var onFailureListener: OnFailureListener? = null
        val task = object : PexelsTask<T> {
            override fun setOnCompleteListener(cb: OnCompleteListener<T>): PexelsTask<T> {
                onCompleteListener = cb
                return this
            }

            override fun setOnSuccessListener(cb: OnSuccessListener<T>): PexelsTask<T> {
                onSuccessListener = cb
                return this
            }

            override fun setOnFailureListener(cb: OnFailureListener): PexelsTask<T> {
                onFailureListener = cb
                return this
            }

            override fun cancel() {
                job.cancel()
            }
        }
        scope.launch {
            try {
                val result = code()
                onSuccessListener?.onSuccess(result)
                onCompleteListener?.onComplete(result, null)
            } catch (e: Exception) {
                onFailureListener?.onFailure(e)
                onCompleteListener?.onComplete(null, e)
            }
        }
        return task
    }

    private fun validateQuery(query: String) {
        if (query.isBlank())
            throw IllegalArgumentException(
                "Query string is blank. Actual query we got: $query."
            )
    }

    private fun validatePage(page: Int?) {
        if (page == null) return
        if (page < 1)
            throw IllegalArgumentException(
                "Page number should be greater than 0. Actual page number we got: $page"
            )
    }

    private fun validatePerPage(perPage: Int?) {
        if (perPage == null) return
        if (perPage !in 15 until 81)
            throw IllegalArgumentException(
                "Per Page should be in range 15 to 80(inclusive). Actual perPage we got: $perPage"
            )
    }

}
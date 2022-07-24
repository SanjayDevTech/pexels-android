package com.pexels.android

import com.pexels.android.network.PexelsService
import com.pexels.android.network.ServiceLocator
import com.pexels.android.operation.PexelsOperation
import com.pexels.android.operation.PexelsOperationImpl
import okhttp3.HttpUrl

/**
 * Main objective of this class is to create a Client
 */
object Pexels {
    private const val BASE_URL = "https://api.pexels.com"

    private val service: PexelsService = ServiceLocator.pexelsService(HttpUrl.get(BASE_URL))

    /**
     * Creates a [PexelsClient] with apiKey provided
     * @param apiKey
     * Get the apiKey from [API Key](https://www.pexels.com/api/new/)
     *
     * @return [PexelsClient]
     */
    @JvmStatic
    fun createClient(
        apiKey: String,
    ): PexelsClient {
        val operation: PexelsOperation = PexelsOperationImpl(
            apiKey,
            service,
        )
        return PexelsClient(operation = operation)
    }
}
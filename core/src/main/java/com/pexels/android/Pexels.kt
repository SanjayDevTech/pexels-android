package com.pexels.android

import com.pexels.android.operation.PexelsOperation
import com.pexels.android.operation.PexelsOperationImpl

/**
 * Main objective of this class is to create a Client
 */
object Pexels {

    private val operation: PexelsOperation = PexelsOperationImpl()

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
    ) = PexelsClient(apiKey = apiKey, operation = operation)
}
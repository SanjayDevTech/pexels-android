package com.pexels.sample

import com.pexels.android.Pexels

object ServiceLocator {
    private const val apiKey: String = BuildConfig.API_KEY
    val client by lazy { Pexels.createClient(apiKey) }
}
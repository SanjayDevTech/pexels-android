package com.pexels.android.operation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pexels.android.assertExceptionSuspend
import com.pexels.android.network.ServiceLocator
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException


class PexelsOperationImplTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var pexelsOperation: PexelsOperation

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        val pexelsService = ServiceLocator.pexelsService(mockWebServer.url("/"))
        pexelsOperation = PexelsOperationImpl("", pexelsService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun searchForPhotos_serverReturns500_throwsHttpException() = runBlocking {
        val response = MockResponse()
            .setResponseCode(500)
        mockWebServer.enqueue(response)
        assertExceptionSuspend {
            pexelsOperation.searchForPhotos(
                query = "Space",
            )
        }.isException(HttpException::class.java)
    }

    @Test
    fun searchForPhotos_serverReturns400_throwsHttpException() = runBlocking {
        val response = MockResponse()
            .setResponseCode(400)
        mockWebServer.enqueue(response)
        assertExceptionSuspend {
            pexelsOperation.searchForPhotos(
                query = "Space",
            )
        }.isException(HttpException::class.java)
    }

    @Test
    fun searchForPhotos_serverReturns300_throwsHttpException() = runBlocking {
        val response = MockResponse()
            .setResponseCode(300)
        mockWebServer.enqueue(response)
        assertExceptionSuspend {
            pexelsOperation.searchForPhotos(
                query = "Space",
            )
        }.isException(HttpException::class.java)
    }

    @Test
    fun searchForPhotos_serverReturns200_throwsNoException() = runBlocking {
        val response = MockResponse()
            .setResponseCode(200)
            .setBody("{}")
        mockWebServer.enqueue(response)
        assertExceptionSuspend {
            pexelsOperation.searchForPhotos(
                query = "Space",
            )
        }.isNoException()
    }

    @Test
    fun searchForPhotos_serverShutdown_throwsException() = runBlocking {
        val response = MockResponse()
            .setResponseCode(200)
            .setBody("{}")
        mockWebServer.enqueue(response)
        mockWebServer.shutdown()
        assertExceptionSuspend {
            pexelsOperation.searchForPhotos(
                query = "Space",
            )
        }.isException(Exception::class.java)
    }
}
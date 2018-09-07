package com.movienight

import com.movienight.data.TestDataLoader
import java.io.IOException
import java.util.HashMap
import java.util.concurrent.CountDownLatch

import javax.inject.Inject
import javax.inject.Singleton

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import timber.log.Timber

@Singleton
class MockServer @Inject constructor() {

    private val restServer = MockWebServer()
    private val dispatchRoutes = HashMap<String, MockResponse>()
    private val testDataLoader = TestDataLoader()

    init {
        startServers()
        setDispatcher()
    }

    private fun startServers() {
        val serverLatch = CountDownLatch(1)

        Thread {
            try {
                restServer.start()
                serverLatch.countDown()
            } catch (e: IOException) {
                Timber.e(e, "Error starting Mock Server")
            }
        }.start()

        try {
            serverLatch.await()
        } catch (e: InterruptedException) {
            Timber.e(e, "Error waiting for Mock Server to start")
        }
    }

    fun url(): String {
        return restServer.url("").url().toString()
    }

    private fun setDispatcher() {
        restServer.setDispatcher(object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(recordedRequest: RecordedRequest): MockResponse {
                val path = recordedRequest.path
                return dispatchRoutes[path] ?: throw IllegalStateException("Response for : $path was not enqueued")
            }
        })
    }

    private fun enqueueSuccess(urlPath: String, body: String) {
        dispatchRoutes[urlPath] = MockResponse().setResponseCode(200).setBody(body)
    }

    private fun enqueueFailure(urlPath: String, code: Int) {
        dispatchRoutes[urlPath] = MockResponse().setResponseCode(code)
    }

    fun setTopRatedMovies() {
        enqueueSuccess("/3/movie/top_rated", testDataLoader.loadString("toprated_movies.json"))
    }
}



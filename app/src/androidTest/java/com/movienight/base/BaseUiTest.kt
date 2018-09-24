package com.movienight.base

import android.support.test.InstrumentationRegistry
import com.movienight.MockServer
import com.movienight.MovieNightTestApplication
import org.junit.After
import org.junit.Before
import javax.inject.Inject

open class BaseUiTest {

    @Inject
    lateinit var mockServer: MockServer

    @Before
    fun setUp() {
        val app = InstrumentationRegistry.getTargetContext().applicationContext as MovieNightTestApplication
        app.applicationTestComponent.inject(this)
    }

    @After
    fun tearDown() {}
}
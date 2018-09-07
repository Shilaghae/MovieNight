package com.movienight.base

import android.support.test.InstrumentationRegistry
import com.movienight.MovieNightTestApplication
import com.movienight.MockServer
import com.movienight.feature.home.HomeViewModel
import org.junit.After
import org.junit.Before
import javax.inject.Inject

open class BaseUiTest {

    @Inject
    lateinit var mockServer: MockServer

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        val clearScoreApp = InstrumentationRegistry.getTargetContext().applicationContext as MovieNightTestApplication
        clearScoreApp.applicationTestComponent.inject(this)
    }

    @After
    fun tearDown() {

    }
}
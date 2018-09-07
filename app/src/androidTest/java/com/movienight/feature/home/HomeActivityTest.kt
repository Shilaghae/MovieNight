package com.movienight.feature.home

import android.support.test.rule.ActivityTestRule
import com.movienight.base.BaseUiTest
import org.junit.Rule
import org.junit.Test


class HomeActivityTest: BaseUiTest() {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java,
            false,
            true)

    @Test
    fun testUserRetrieveTopRatedMovies() {
        mockServer.setTopRatedMovies()
    }
}
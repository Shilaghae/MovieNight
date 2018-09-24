package com.movienight.feature.home

import android.support.test.rule.ActivityTestRule
import com.movienight.R
import com.movienight.base.BaseUiTest
import com.movienight.util.AssertUtils
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
        val movies = arrayListOf("Dilwale Dulhania Le Jayenge", "The Shawshank Redemption")

        AssertUtils.assertRecyclerViewItemsMatchList(R.id.activity_home_recyclerView, R.id.movie_item_textView_movieTitle, movies);
    }
}
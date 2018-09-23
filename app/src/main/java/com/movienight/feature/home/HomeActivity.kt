package com.movienight.feature.home

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.movienight.R
import com.movienight.base.BaseActivity
import com.movienight.base.view.ViewError
import com.movienight.base.view.ViewErrorHandler
import com.movienight.data.Movie
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), ViewError {

    @Inject
    lateinit var homeViewModule: HomeViewModel

    @Inject
    lateinit var homeViewErrorHandler: HomeViewErrorHandler

    val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity_home_recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
            setHasFixedSize(true)
        }

        homeViewModule.apply {
            loadingLiveDate.observe(this@HomeActivity, Observer { showLoading(it!!) })
            errorLiveData.observe(this@HomeActivity, Observer { it?.invoke()!! })
            moviesLiveData.observe(this@HomeActivity, Observer { showMovies(it!!) })
        }

        homeViewModule.retrieveData()
    }

    override fun getViewErrorHandler(): ViewErrorHandler {
        return homeViewErrorHandler;
    }

    override fun getViewError(): ViewError {
        return this
    }

    private fun showMovies(movies: List<Movie>) {
        activity_home_moMovieToShow.visibility = if (movies.isEmpty()) View.VISIBLE else View.INVISIBLE
        movieAdapter.setMovies(movies)
    }

    private fun showLoading(isLoading: Boolean) {
        activity_home_blackBackground.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun getActivityViewId(): Int {
        return R.layout.activity_home
    }

    override fun onConnectivityAvailable() = {
        Snackbar.make(activity_home_constraintLayout, "Connectivity available!", Snackbar.LENGTH_LONG).show()
    }

    override fun onConnectivityUnavailable() = {
        Snackbar.make(activity_home_constraintLayout, "Connectivity unavailable!", Snackbar.LENGTH_LONG).show()
    }

    override fun onGenericError() = {
        Snackbar.make(activity_home_constraintLayout, "Generic Error!", Snackbar.LENGTH_LONG).show()
    }


    override fun userNotAuthenticated() = {
        Snackbar.make(activity_home_constraintLayout, "User not authenticated!", Snackbar.LENGTH_LONG).show()
    }

    override fun onResourceNotFound() = {
        Snackbar.make(activity_home_constraintLayout, "Resource not available!", Snackbar.LENGTH_LONG).show()
    }

    override fun onForbidden() =  {
        Snackbar.make(activity_home_constraintLayout, "Forbidden", Snackbar.LENGTH_LONG).show()
    }
}

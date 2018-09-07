package com.movienight.feature.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.movienight.R
import com.movienight.base.BaseActivity
import com.movienight.data.Movie
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var homeViewModule: HomeViewModel

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
            errorLiveData.observe(this@HomeActivity, Observer { showError(it!!) })
            moviesLiveData.observe(this@HomeActivity, Observer { showMovies(it!!) })
        }

        homeViewModule.retrieveData()
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

    fun showError(error: String) {
        Snackbar.make(activity_home_constraintLayout, error, Snackbar.LENGTH_LONG).show()
    }
}

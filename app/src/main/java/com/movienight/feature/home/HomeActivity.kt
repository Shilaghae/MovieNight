package com.movienight.feature.home

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter.createFromResource
import com.movienight.R
import com.movienight.base.BaseActivity
import com.movienight.data.Movie
import com.movienight.feature.detail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), AdapterView.OnItemSelectedListener {


    @Inject
    lateinit var homeViewModule: HomeViewModel

    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity_home_recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            movieAdapter = MovieAdapter(homeViewModule)
            adapter = movieAdapter
            setHasFixedSize(true)
        }

        homeViewModule.apply {
            loadingLiveDate.observe(this@HomeActivity, Observer { showLoading(it!!) })
            errorLiveData.observe(this@HomeActivity, Observer { showError(it!!) })
            repositoryMovieLiveData.observe(this@HomeActivity, Observer { showMovies(it!!) })
            onMovieClickedLiveData.observe(this@HomeActivity, Observer { showMovie(it) })
        }

        homeViewModule.retrieveData()
    }

    private fun showMovies(movies: List<Movie>) {
        activity_home_moMovieToShow.visibility = if (movies.isEmpty()) View.VISIBLE else View.INVISIBLE
        movieAdapter.setMovies(movies)
    }

    private fun showMovie(movie: Movie?) {
        if (movie != null) {
            Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.MOVIE_EXTRA, movie)
            startActivity(intent)
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.select_movies_menu, menu)
        val menuItem =  menu!!.findItem(R.id.select_movie_menu_spinner)
        val spinner = menuItem.actionView as Spinner
        val adapter = createFromResource(this, R.array.select_movies, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setOnItemSelectedListener(this@HomeActivity)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val menuItem = view as TextView
        when(menuItem.text) {
            resources.getString(R.string.top_rated) -> homeViewModule.getTopRatedMovieService()
            resources.getString(R.string.most_populare) -> homeViewModule.getTopRatedMovieService()
        }
    }
}

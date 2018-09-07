package com.movienight.feature.detail

import android.os.Bundle
import com.movienight.R
import com.movienight.base.BaseActivity
import com.movienight.data.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    var movie: Movie? = null

    companion object {
        const val MOVIE_EXTRA: String = "MOVIE_EXTRA";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.hasExtra(MOVIE_EXTRA)) {
            movie = intent.getParcelableExtra(MOVIE_EXTRA)
            activity_movie_detail_textView_movieTitle.setText(movie!!.title)
        }
    }

    override fun getActivityViewId(): Int {
        return R.layout.activity_movie_detail
    }
}
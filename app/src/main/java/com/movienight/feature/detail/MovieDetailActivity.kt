package com.movienight.feature.detail

import android.os.Bundle
import com.movienight.R
import com.movienight.base.BaseActivity
import com.movienight.base.view.ViewError
import com.movienight.base.view.ViewErrorHandler
import com.movienight.data.Movie
import com.movienight.feature.home.HomeViewErrorHandler
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity(), ViewError {
    override fun onConnectivityUnavailable(): (() -> Unit)? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectivityAvailable(): (() -> Unit)? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGenericError(): (() -> Unit)? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun userNotAuthenticated(): (() -> Unit)? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResourceNotFound(): (() -> Unit)? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onForbidden(): (() -> Unit)? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewError(): ViewError {
        return this;
    }

    override fun getViewErrorHandler(): ViewErrorHandler {
        return HomeViewErrorHandler()
    }

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
package com.movienight.base

import android.app.Activity
import android.os.Bundle
import com.movienight.MovieNightApp
import com.movienight.base.view.ViewError
import com.movienight.base.view.ViewErrorHandler
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityViewId())
        getViewErrorHandler().setViewError(getViewError())
    }

    abstract fun getActivityViewId(): Int

    abstract fun getViewError(): ViewError

    abstract fun getViewErrorHandler(): ViewErrorHandler
}
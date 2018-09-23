package com.movienight.feature.home

import com.movienight.base.view.ViewError
import com.movienight.base.view.ViewErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeViewErrorHandler @Inject constructor() : ViewErrorHandler {

    private lateinit var viewError: ViewError

    override fun setViewError(viewError: ViewError) {
        this.viewError = viewError
    }

    override fun onGenericError() : (() -> Unit)? {
        return viewError.onGenericError()
    }

    override fun onResourceNoFound() : (() -> Unit)? {
        return viewError.onResourceNotFound()
    }

    override fun onForbidden() : (() -> Unit)? {
        return viewError.onForbidden()
    }

    override fun onNetworkError() : (() -> Unit)?{
        return viewError.onConnectivityUnavailable()
    }
}
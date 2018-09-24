package com.movienight.base.view

interface ViewErrorHandler {

    fun onNetworkError() : (() -> Unit)?
    fun onGenericError() : (() -> Unit)?
    fun onResourceNoFound() : (() -> Unit)?
    fun onForbidden() : (() -> Unit)?

    fun setViewError(viewError: ViewError)
}
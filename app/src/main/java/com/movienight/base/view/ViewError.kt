package com.movienight.base.view


interface ViewError {

    fun onConnectivityUnavailable() : (() -> Unit)?

    fun onConnectivityAvailable() : (() -> Unit)?

    fun onGenericError() : (() -> Unit)?

    fun userNotAuthenticated() : (() -> Unit)?

    fun onResourceNotFound() : (() -> Unit)?

    fun onForbidden() : (() -> Unit)?
}
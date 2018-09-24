package com.movienight.error

import com.movienight.base.view.ViewErrorHandler
import retrofit2.HttpException
import java.net.UnknownHostException

fun handleException(exception: Throwable, viewErrorHandler: ViewErrorHandler): (() -> Unit)? {
    when {
        exception is HttpException -> {
            when (exception.code()) {
                403 -> return viewErrorHandler.onForbidden()
                404 -> return viewErrorHandler.onResourceNoFound()
                else -> return viewErrorHandler.onGenericError()
            }
        }
        exception is UnknownHostException -> return viewErrorHandler.onNetworkError()
        else -> return viewErrorHandler.onGenericError()
    }
}


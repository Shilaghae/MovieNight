package com.movienight

import com.movienight.base.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import android.net.ConnectivityManager
import android.content.IntentFilter


open class MovieNightApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this)
                .create(this)
    }
}
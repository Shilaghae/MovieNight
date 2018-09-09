package com.movienight.feature.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.movienight.base.NetworkWatcher
import com.movienight.database.MovieDatabase
import com.movienight.service.TopRatedMovieService
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeViewModelFactory constructor(val activity: HomeActivity, val topRatedMovieService: TopRatedMovieService, val movieDatabase : MovieDatabase, val networkWatcher: NetworkWatcher) : ViewModelFactory {

    override fun getViewModel(): HomeViewModel {
        return ViewModelProviders.of(
                activity,
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                        return HomeViewModel(AndroidSchedulers.mainThread(), topRatedMovieService, movieDatabase, networkWatcher) as T
                    }
                })
                .get(HomeViewModel::class.java)

    }
}
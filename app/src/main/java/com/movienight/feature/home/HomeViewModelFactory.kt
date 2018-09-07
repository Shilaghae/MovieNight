package com.movienight.feature.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.movienight.service.PopularMovieService
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeViewModelFactory constructor(val activity: HomeActivity, val popularMovieService: PopularMovieService) : ViewModelFactory {

    override fun getViewModel(): HomeViewModel {
        return ViewModelProviders.of(
                activity,
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                        return HomeViewModel(AndroidSchedulers.mainThread(), popularMovieService) as T
                    }
                })
                .get(HomeViewModel::class.java)

    }

}
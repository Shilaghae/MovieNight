package com.movienight.feature.home

import com.movienight.base.view.ViewError
import com.movienight.base.view.ViewErrorHandler
import dagger.Binds
import dagger.Module

@Module
abstract class HomeModule {

    @Binds
    abstract fun bindHomeViewModelFactory(homeViewModelFactory: HomeViewModelFactory): ViewModelFactory

    @Binds
    abstract fun bindViewErrorHandler(homeViewErrorHandler: HomeViewErrorHandler): ViewErrorHandler

}
package com.movienight.base

import com.movienight.service.ApiService
import com.movienight.service.NetworkService
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun getNetworkService(apiService: ApiService): NetworkService
}
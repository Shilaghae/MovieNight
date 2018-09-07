package com.movienight.base

import com.movienight.service.ApiTestService
import com.movienight.service.NetworkService
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationTestModule {

    @Binds
    abstract fun getNetworkService(apiService: ApiTestService): NetworkService
}
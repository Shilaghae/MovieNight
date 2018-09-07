package com.movienight.base

import com.movienight.feature.home.HomeActivity
import com.movienight.feature.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeActivity(): HomeActivity
}

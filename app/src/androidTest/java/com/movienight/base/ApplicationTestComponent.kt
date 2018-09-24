package com.movienight.base

import android.app.Activity
import com.movienight.MovieNightTestApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    RxTestModule::class,
    ApplicationTestModule::class])

interface ApplicationTestComponent : AndroidInjector<MovieNightTestApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MovieNightTestApplication>()

    fun injectActivity(activity: Activity)
}
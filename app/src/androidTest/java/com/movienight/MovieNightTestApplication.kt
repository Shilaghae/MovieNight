package com.movienight

import com.movienight.base.ApplicationTestComponent
import com.movienight.base.DaggerApplicationTestComponent
import dagger.android.AndroidInjector

class MovieNightTestApplication : MovieNightApp() {

    lateinit var applicationTestComponent: ApplicationTestComponent

    override fun applicationInjector(): AndroidInjector<out dagger.android.DaggerApplication> {
        applicationTestComponent = DaggerApplicationTestComponent.builder()
                .create(this) as ApplicationTestComponent
        return applicationTestComponent
    }
}
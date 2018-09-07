package com.movienight.base

import com.movienight.MovieNightApp
import com.movienight.base.RxModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    RxModule::class,
    ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<MovieNightApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MovieNightApp>()
}

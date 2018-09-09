package com.movienight.base

import android.app.Application
import android.content.Context
import com.movienight.MovieNightApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.BindsInstance



@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    RxModule::class,
    ApplicationModule::class,
    DatabaseModule::class])
interface ApplicationComponent : AndroidInjector<MovieNightApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MovieNightApp>() {
        @BindsInstance abstract fun application(application : Application): Builder
    }
}

package com.movienight.base

import android.app.Application
import android.content.Context
import com.movienight.database.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(application: Application) : MovieDatabase {
        return MovieDatabase.build(application)
    }
}
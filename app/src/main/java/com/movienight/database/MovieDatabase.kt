package com.movienight.database

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.movienight.data.Movie
import android.arch.persistence.room.Room

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object Instance {

        private var instance: MovieDatabase? = null

        fun build(application: Application): MovieDatabase {
            if(instance == null) {
                synchronized(MovieDatabase::class){
                    if(instance == null){
                        instance = Room.databaseBuilder(application.applicationContext, MovieDatabase::class.java, "movie_database").build()
                    }
                }
            }
            return instance!!
        }
    }
}

package com.movienight.service

import com.movienight.BuildConfig
import com.movienight.data.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PopularMovieService @Inject constructor(apiService: ApiService) {

    companion object {
        private const val apiKey = BuildConfig.THEMOVIEBD_API_KEY
    }

    val popularMovieServiceApi: PopularMovieServiceApi

    init {
        popularMovieServiceApi = apiService.retrofit.create(PopularMovieServiceApi::class.java)
    }

    open fun getMostPopularMovies(): Observable<List<Movie>> {
        return popularMovieServiceApi.mostPopularMovies(apiKey).map { it.results }
    }

    interface PopularMovieServiceApi {
        @GET("3/movie/popular")
        fun mostPopularMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>
    }
}
package com.movienight.service

import com.movienight.BuildConfig
import com.movienight.data.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class TopRatedMovieService @Inject constructor(apiService: NetworkService) {

    companion object {
        private const val apiKey = BuildConfig.THEMOVIEBD_API_KEY
    }

    val topRatedMovieServiceApi: PopularMovieServiceApi

    init {
        topRatedMovieServiceApi = apiService.getRetrofitInstance().create(PopularMovieServiceApi::class.java)
    }

    open fun getTopRatedMovies(): Observable<List<Movie>> {
        return topRatedMovieServiceApi.mostPopularMovies(apiKey).map { it.results }
    }

    interface PopularMovieServiceApi {
        @GET("3/movie/top_rated")
        fun mostPopularMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>
    }
}
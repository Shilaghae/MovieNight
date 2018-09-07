package com.movienight.service

import com.movienight.data.Movie

data class MovieResponse(override val results: List<Movie>) : ApiResponse<Movie>
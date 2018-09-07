package com.movienight.data

data class Movie(val title: String,
        val vote_average: String? = "0",
        val id: Int,
        val poster_path: String? = null,
        val overview: String? = null,
        val release_date: String? = null)
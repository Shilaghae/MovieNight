package com.movienight.service

interface ApiResponse<T> {
    val results: List<T>;
}
package com.movienight.service

import retrofit2.Retrofit

interface NetworkService {
    fun getRetrofitInstance() : Retrofit
}
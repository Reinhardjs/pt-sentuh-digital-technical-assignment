package com.example.myapplication.core.data.source.remote.network

import com.example.myapplication.core.data.source.remote.response.JokeResponse
import com.example.myapplication.core.data.source.remote.response.ListJokeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun searchJokes(@Query("query") query: String): ListJokeResponse

    @GET("random")
    suspend fun getRandomJoke(): JokeResponse
}
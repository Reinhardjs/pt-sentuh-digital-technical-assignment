package com.example.myapplication.core.domain.repository

import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.data.source.remote.response.JokeResponse
import kotlinx.coroutines.flow.Flow

interface IJokeRepository {
    fun getRandomJoke(): Flow<Resource<JokeResponse>>

    fun searchJokes(query: String): Flow<Resource<List<JokeResponse>>>
}
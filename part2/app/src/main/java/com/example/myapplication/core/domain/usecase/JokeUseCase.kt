package com.example.myapplication.core.domain.usecase

import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.data.source.remote.response.JokeResponse
import kotlinx.coroutines.flow.Flow

interface JokeUseCase {
    fun getRandomJoke(): Flow<Resource<JokeResponse>>

    fun searchJokes(query: String): Flow<Resource<List<JokeResponse>>>
}
package com.example.myapplication.core.domain.usecase

import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeUseCase {
    fun getRandomJoke(): Flow<Resource<Joke>>

    fun searchJokes(query: String): Flow<Resource<List<Joke>>>
}
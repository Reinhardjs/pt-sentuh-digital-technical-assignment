package com.example.myapplication.core.domain.repository

import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface IJokeRepository {
    fun getRandomJoke(): Flow<Resource<Joke>>

    fun searchJokes(): Flow<Resource<List<Joke>>>
}
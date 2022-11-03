package com.example.myapplication.core.domain.usecase

import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.data.source.remote.response.JokeResponse
import com.example.myapplication.core.domain.repository.IJokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JokeInteractor @Inject constructor(private val jokeRepository: IJokeRepository) :
    JokeUseCase {
    override fun getRandomJoke(): Flow<Resource<JokeResponse>> = jokeRepository.getRandomJoke()

    override fun searchJokes(query: String): Flow<Resource<List<JokeResponse>>> = jokeRepository.searchJokes(query)
}
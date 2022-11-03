package com.example.myapplication.core.domain.usecase

import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.domain.model.Joke
import com.example.myapplication.core.domain.repository.IJokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JokeInteractor @Inject constructor(private val jokeRepository: IJokeRepository) :
    JokeUseCase {
    override fun getRandomJoke(): Flow<Resource<Joke>> = jokeRepository.getRandomJoke()

    override fun searchJokes(query: String): Flow<Resource<List<Joke>>> = jokeRepository.searchJokes(query)
}
package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.data.source.remote.response.JokeResponse
import com.example.myapplication.core.domain.usecase.JokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val jokeUseCase: JokeUseCase) : ViewModel() {
    val randomJoke = jokeUseCase.getRandomJoke().asLiveData()

    fun searchJokes(query: String): LiveData<Resource<List<JokeResponse>>> =
        jokeUseCase.searchJokes(query).asLiveData()
}
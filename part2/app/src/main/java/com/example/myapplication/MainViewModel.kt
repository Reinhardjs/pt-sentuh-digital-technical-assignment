package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication.core.domain.usecase.JokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(jokeUseCase: JokeUseCase) : ViewModel() {
    val randomJoke = jokeUseCase.getRandomJoke().asLiveData()
}
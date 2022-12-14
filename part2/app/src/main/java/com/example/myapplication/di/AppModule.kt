package com.example.myapplication.di

import com.example.myapplication.core.domain.usecase.JokeInteractor
import com.example.myapplication.core.domain.usecase.JokeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideJokeUseCase(jokeInteractor: JokeInteractor): JokeUseCase

}
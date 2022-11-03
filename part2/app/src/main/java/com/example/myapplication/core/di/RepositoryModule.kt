package com.example.myapplication.core.di

import com.example.myapplication.core.data.JokeRepository
import com.example.myapplication.core.domain.repository.IJokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(jokeRepository: JokeRepository): IJokeRepository

}
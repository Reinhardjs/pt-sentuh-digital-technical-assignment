package com.example.myapplication.core.data

import com.example.myapplication.core.data.source.remote.RemoteDataSource
import com.example.myapplication.core.data.source.remote.network.ApiResponse
import com.example.myapplication.core.data.source.remote.response.JokeResponse
import com.example.myapplication.core.domain.repository.IJokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IJokeRepository {

    override fun getRandomJoke(): Flow<Resource<JokeResponse>> =
        object : NetworkBoundResource<JokeResponse>() {
            override suspend fun createCall(): Flow<ApiResponse<JokeResponse>> =
                remoteDataSource.getRandomJoke()
        }.asFlow()

    override fun searchJokes(query: String): Flow<Resource<List<JokeResponse>>> =
        object : NetworkBoundResource<List<JokeResponse>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<JokeResponse>>> =
                remoteDataSource.searchJokes(query)
        }.asFlow()
}

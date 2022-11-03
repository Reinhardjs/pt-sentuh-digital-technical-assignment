package com.example.myapplication.core.data.source.remote

import android.util.Log
import com.example.myapplication.core.data.source.remote.network.ApiResponse
import com.example.myapplication.core.data.source.remote.network.ApiService
import com.example.myapplication.core.data.source.remote.response.JokeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getRandomJoke(): Flow<ApiResponse<JokeResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getRandomJoke()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchJokes(query: String): Flow<ApiResponse<List<JokeResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.searchJokes(query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty(response.results))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
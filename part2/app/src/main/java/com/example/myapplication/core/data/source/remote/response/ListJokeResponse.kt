package com.example.myapplication.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListJokeResponse(
    @field:SerializedName("results")
    val results: List<JokeResponse>
)
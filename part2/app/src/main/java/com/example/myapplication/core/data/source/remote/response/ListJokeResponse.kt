package com.example.myapplication.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListJokeResponse(
    @field:SerializedName("result")
    val results: List<JokeResponse>
)
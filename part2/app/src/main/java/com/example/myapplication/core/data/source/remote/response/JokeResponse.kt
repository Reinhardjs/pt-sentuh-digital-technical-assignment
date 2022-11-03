package com.example.myapplication.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("value")
    val value: String
)

package com.example.myapplication.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Joke(
    val createdAt: String,
    val updatedAt: String,
    val url: String,
    val value: String
) : Parcelable
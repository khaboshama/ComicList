package com.khaled.comiclist.feature.module.domain

import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("num")
    val number: Int,
    val title: String,
    @SerializedName("alt")
    val description: String,
    @SerializedName("img")
    val imageUrl: String
)
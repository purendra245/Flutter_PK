package com.example.model

import com.google.gson.annotations.SerializedName

data class AlbumModelItem(
    val id: Int,
    val title: String,
    val userId: Int
)
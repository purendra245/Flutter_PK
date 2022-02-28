package com.example.demoproject.data.model.people

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_artists")
data class People(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val gender: Int,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String
)
package com.example.demoproject.data.repository.movie.datasource

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People

interface ArtistLocalDataSource {

    suspend fun getPeopleFromDb():List<People>
    suspend fun savePeopleFromDb(list: List<People>)
    suspend fun clearDb()
}
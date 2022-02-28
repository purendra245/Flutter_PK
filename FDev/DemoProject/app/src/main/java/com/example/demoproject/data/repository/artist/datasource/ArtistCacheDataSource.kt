package com.example.demoproject.data.repository.movie.datasource

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People

interface ArtistCacheDataSource {

    suspend fun getPeopleFromCache():List<People>
    suspend fun setPeopleDataCache(movies: List<People>)
}
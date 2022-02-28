package com.example.demoproject.data.repository.movie.datasource

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult

interface TvLocalDataSource {

    suspend fun getTvFromDb():List<TvResult>
    suspend fun saveTvFromDb(list: List<TvResult>)
    suspend fun clearDb()
}
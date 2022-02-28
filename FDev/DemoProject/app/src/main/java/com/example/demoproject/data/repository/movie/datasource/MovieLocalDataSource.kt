package com.example.demoproject.data.repository.movie.datasource

import com.example.demoproject.data.model.movie.Movie

interface MovieLocalDataSource {

    suspend fun getMoviesFromDb():List<Movie>
    suspend fun saveMoviesFromDb(list: List<Movie>)
    suspend fun clearDb()
}
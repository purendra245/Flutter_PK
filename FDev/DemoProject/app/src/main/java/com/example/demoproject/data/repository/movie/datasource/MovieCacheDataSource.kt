package com.example.demoproject.data.repository.movie.datasource

import com.example.demoproject.data.model.movie.Movie

interface MovieCacheDataSource {

    suspend fun getMovieFromCache():List<Movie>
    suspend fun setMovieDataCache(movies: List<Movie>)
}
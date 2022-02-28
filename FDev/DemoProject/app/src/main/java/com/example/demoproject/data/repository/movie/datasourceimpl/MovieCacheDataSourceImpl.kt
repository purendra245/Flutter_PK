package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.repository.movie.datasource.MovieCacheDataSource

class MovieCacheDataSourceImpl() :
    MovieCacheDataSource {

    private var movieList = ArrayList<Movie>()

    override suspend fun getMovieFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun setMovieDataCache(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
    }

}
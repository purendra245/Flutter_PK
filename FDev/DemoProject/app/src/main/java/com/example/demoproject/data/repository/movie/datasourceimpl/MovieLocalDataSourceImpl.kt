package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.dao.MovieDao
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(val movieDao:MovieDao) :
    MovieLocalDataSource {
    override suspend fun getMoviesFromDb(): List<Movie>  = movieDao.getMovie()

    override suspend fun saveMoviesFromDb(list: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovie(list)
        }
    }

    override suspend fun clearDb() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteMovie()
        }
    }


}
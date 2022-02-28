package com.example.demoproject.data.repository

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.movie.PopularMovie
import com.example.demoproject.data.repository.movie.datasource.MovieCacheDataSource
import com.example.demoproject.data.repository.movie.datasource.MovieLocalDataSource
import com.example.demoproject.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.demoproject.domain.repository.MovieRepository
import retrofit2.Response
import java.lang.Exception

class MovieRepositoryImpl( private val movieRemoteDataSource: MovieRemoteDataSource
                           ,private val movieLocalDataSource: MovieLocalDataSource
                           ,private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {




    override suspend fun getMovies(): List<Movie> {
       return getMovieFromCache()
    }

    override suspend fun updateMovies(): List<Movie> {
        val newListOfMovie:List<Movie> = getMovieFromAPI()
        movieLocalDataSource.clearDb()
        movieLocalDataSource.saveMoviesFromDb(newListOfMovie)
        movieCacheDataSource.setMovieDataCache(newListOfMovie)
        return newListOfMovie
    }

    suspend fun getMovieFromAPI():List<Movie>{
        lateinit var list: List<Movie>
        try {

            val response: Response<PopularMovie> = movieRemoteDataSource.getMovie()
            val body : PopularMovie ?= response.body()
            if(body!=null){
                list = body.results
            }


        }catch (e:Exception){

        }

        return list
    }

    suspend fun getMovieFromDb():List<Movie>{

        lateinit var list: List<Movie>
        try {
            val listMovie:List<Movie> = movieLocalDataSource.getMoviesFromDb()
             if(listMovie.size>0){
                list = listMovie
            }else{
                 list = getMovieFromAPI()
                 movieLocalDataSource.saveMoviesFromDb(list)
             }

        }catch (e:Exception){

        }

        return list
    }

    suspend fun getMovieFromCache():List<Movie>{
        lateinit var list: List<Movie>
        try {
            val listMovie:List<Movie> = movieCacheDataSource.getMovieFromCache()
            if(listMovie.size>0){
                list = listMovie
            }else{
                list = getMovieFromDb()
                movieCacheDataSource.setMovieDataCache(list)
            }

        }catch (e:Exception){

        }

        return list
    }
}
package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.api.datasource.TMDBService
import com.example.demoproject.data.model.movie.PopularMovie
import com.example.demoproject.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(val tmdb:TMDBService,val apiKey:String) : MovieRemoteDataSource {
    override suspend fun getMovie(): Response<PopularMovie>
            = tmdb.getPopularMovie(apiKey)

}
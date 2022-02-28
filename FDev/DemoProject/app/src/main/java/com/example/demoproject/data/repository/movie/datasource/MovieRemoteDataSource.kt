package com.example.demoproject.data.repository.movie.datasource

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.movie.PopularMovie
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovie():Response<PopularMovie>
}
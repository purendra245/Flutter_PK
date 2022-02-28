package com.example.demoproject.data.repository.movie.datasource

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.movie.PopularMovie
import com.example.demoproject.data.model.people.PopularPerson
import com.example.demoproject.data.model.tv.PopularTv
import retrofit2.Response

interface TvRemoteDataSource {

    suspend fun getTv():Response<PopularTv>
}
package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.api.datasource.TMDBService
import com.example.demoproject.data.model.people.PopularPerson
import com.example.demoproject.data.model.tv.PopularTv
import com.example.demoproject.data.repository.movie.datasource.TvRemoteDataSource
import retrofit2.Response

class TvRemoteDataSourceImpl(val tmdb:TMDBService, val apiKey:String) : TvRemoteDataSource {

    override suspend fun getTv(): Response<PopularTv> {
      return tmdb.getPopularTv(apiKey)
    }


}
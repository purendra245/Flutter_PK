package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.api.datasource.TMDBService
import com.example.demoproject.data.model.people.PopularPerson
import com.example.demoproject.data.repository.movie.datasource.ArtistRemoteDataSource
import com.example.demoproject.data.repository.movie.datasource.TvRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(val tmdb:TMDBService, val apiKey:String) : ArtistRemoteDataSource {

    override suspend fun getPeople(): Response<PopularPerson> {
      return tmdb.getPopularPeople(apiKey)
    }


}
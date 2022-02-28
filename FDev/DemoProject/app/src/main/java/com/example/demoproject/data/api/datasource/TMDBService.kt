package com.example.demoproject.data.api.datasource

import com.example.demoproject.data.model.movie.PopularMovie
import com.example.demoproject.data.model.people.PopularPerson
import com.example.demoproject.data.model.tv.PopularTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key")  apiKey:String): Response<PopularMovie>

    @GET("tv/popular")
    suspend fun getPopularTv(@Query("api_key")  apiKey:String): Response<PopularTv>

    @GET("person/popular")
    suspend fun getPopularPeople(@Query("api_key")  apiKey:String): Response<PopularPerson>
}
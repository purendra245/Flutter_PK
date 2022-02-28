package com.example.demoproject.domain.repository

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult

interface TvShowRepository {


    //Popular Tv Shows
    suspend fun geTvShow():List<TvResult>
    suspend fun updateTvShow():List<TvResult>

}
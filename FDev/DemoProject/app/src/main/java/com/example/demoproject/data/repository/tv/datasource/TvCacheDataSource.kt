package com.example.demoproject.data.repository.movie.datasource

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult

interface TvCacheDataSource {

    suspend fun getTvFromCache():List<TvResult>
    suspend fun setTvDataCache(movies: List<TvResult>)
}
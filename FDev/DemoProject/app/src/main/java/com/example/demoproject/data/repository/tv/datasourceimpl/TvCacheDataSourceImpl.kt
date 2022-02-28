package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult
import com.example.demoproject.data.repository.movie.datasource.TvCacheDataSource

class TvCacheDataSourceImpl() : TvCacheDataSource {

    private var tvResult = ArrayList<TvResult>()

    override suspend fun getTvFromCache(): List<TvResult> {
        return tvResult
    }

    override suspend fun setTvDataCache(people : List<TvResult>) {
        tvResult.clear()
        tvResult.addAll(people)
    }



}
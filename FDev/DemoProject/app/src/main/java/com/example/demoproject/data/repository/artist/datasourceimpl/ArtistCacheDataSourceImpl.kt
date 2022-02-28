package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.repository.movie.datasource.ArtistCacheDataSource
import com.example.demoproject.data.repository.movie.datasource.TvCacheDataSource

class ArtistCacheDataSourceImpl() : ArtistCacheDataSource {

    private var peopleList = ArrayList<People>()

    override suspend fun getPeopleFromCache(): List<People> {
        return peopleList
    }

    override suspend fun setPeopleDataCache(people : List<People>) {
        peopleList.clear()
        peopleList.addAll(people)
    }



}
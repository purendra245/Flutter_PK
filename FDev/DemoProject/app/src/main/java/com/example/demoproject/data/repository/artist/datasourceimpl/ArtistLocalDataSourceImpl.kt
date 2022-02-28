package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.dao.PopularPeopleDao
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.repository.movie.datasource.ArtistLocalDataSource
import com.example.demoproject.data.repository.movie.datasource.TvLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(val peopleDao:PopularPeopleDao) : ArtistLocalDataSource {

    override suspend fun getPeopleFromDb(): List<People>  = peopleDao.getPopularPeople()

    override suspend fun savePeopleFromDb(list: List<People>) {
        CoroutineScope(Dispatchers.IO).launch {
            peopleDao.savePopularPeople(list)
        }
    }

    override suspend fun clearDb() {
        CoroutineScope(Dispatchers.IO).launch {
            peopleDao.deletePopularPeople()
        }
    }


}
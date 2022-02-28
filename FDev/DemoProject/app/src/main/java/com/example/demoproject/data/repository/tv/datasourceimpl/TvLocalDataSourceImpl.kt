package com.example.demoproject.data.repository.movie.datasourceimpl

import com.example.demoproject.data.dao.PopularPeopleDao
import com.example.demoproject.data.dao.PopularTvDao
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult
import com.example.demoproject.data.repository.movie.datasource.TvLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvLocalDataSourceImpl(val popularTvDao:PopularTvDao) : TvLocalDataSource {

    override suspend fun getTvFromDb(): List<TvResult>  = popularTvDao.getTvShow()

    override suspend fun saveTvFromDb(list: List<TvResult>) {
        CoroutineScope(Dispatchers.IO).launch {
            popularTvDao.saveTvShow(list)
        }
    }

    override suspend fun clearDb() {
        CoroutineScope(Dispatchers.IO).launch {
            popularTvDao.deleteTvShow()
        }
    }


}
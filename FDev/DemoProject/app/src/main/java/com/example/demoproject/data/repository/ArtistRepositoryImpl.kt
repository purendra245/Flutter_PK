package com.example.demoproject.data.repository

import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.people.PopularPerson
import com.example.demoproject.data.repository.movie.datasource.*
import com.example.demoproject.domain.repository.PeopleRepository
import retrofit2.Response
import java.lang.Exception

class ArtistRepositoryImpl(private val artistRemoteDataSource: ArtistRemoteDataSource
                           , private val artistLocalDataSource: ArtistLocalDataSource
                           , private val artistCacheDataSource: ArtistCacheDataSource)
    : PeopleRepository {


    suspend fun getPeopleFromAPI():List<People>{
        lateinit var list: List<People>
        try {

            val response: Response<PopularPerson> = artistRemoteDataSource.getPeople()
            val body : PopularPerson ?= response.body()
            if(body!=null){
                list = body.results
            }


        }catch (e:Exception){

        }

        return list
    }

    suspend fun getPeopleFromDb():List<People>{

        lateinit var list: List<People>
        try {
            val listMovie:List<People> = artistLocalDataSource.getPeopleFromDb()
             if(listMovie.size>0){
                list = listMovie
            }else{
                 list = getPeopleFromAPI()
                 artistLocalDataSource.savePeopleFromDb(list)
             }

        }catch (e:Exception){

        }

        return list
    }

    suspend fun getPeopleFromCache():List<People>{
        lateinit var list: List<People>
        try {
            val listMovie:List<People> = artistCacheDataSource.getPeopleFromCache()
            if(listMovie.size>0){
                list = listMovie
            }else{
                list = getPeopleFromDb()
                artistCacheDataSource.setPeopleDataCache(list)
            }

        }catch (e:Exception){

        }

        return list
    }

    override suspend fun getPopularPeople(): List<People> {
        return getPeopleFromCache()
    }

    override suspend fun updatePopularPeople(): List<People> {
        val newListOfMovie:List<People> = getPeopleFromAPI()
        artistLocalDataSource.clearDb()
        artistLocalDataSource.savePeopleFromDb(newListOfMovie)
        artistCacheDataSource.setPeopleDataCache(newListOfMovie)
        return newListOfMovie
    }
}
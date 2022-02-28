package com.example.demoproject.data.repository

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.movie.PopularMovie
import com.example.demoproject.data.model.people.PopularPerson
import com.example.demoproject.data.model.tv.PopularTv
import com.example.demoproject.data.model.tv.TvResult
import com.example.demoproject.data.repository.movie.datasource.*
import com.example.demoproject.domain.repository.MovieRepository
import com.example.demoproject.domain.repository.TvShowRepository
import retrofit2.Response
import java.lang.Exception

class TvRepositoryImpl(private val tvRemoteDataSource: TvRemoteDataSource
                       , private val tvLocalDataSource: TvLocalDataSource
                       , private val tvCacheDataSource: TvCacheDataSource
) : TvShowRepository {


    override suspend fun geTvShow(): List<TvResult> {
        return getTvFromCache()
    }

    override suspend fun updateTvShow(): List<TvResult> {
        val newListOfMovie:List<TvResult> = getTVFromAPI()
        tvLocalDataSource.clearDb()
        tvLocalDataSource.saveTvFromDb(newListOfMovie)
        tvCacheDataSource.setTvDataCache(newListOfMovie)
        return newListOfMovie
    }



    suspend fun getTVFromAPI():List<TvResult>{
        lateinit var list: List<TvResult>
        try {

            val response: Response<PopularTv> = tvRemoteDataSource.getTv()
            val body : PopularTv ?= response.body()
            if(body!=null){
                list = body.results
            }


        }catch (e:Exception){

        }

        return list
    }

    suspend fun getTvFromDb():List<TvResult>{

        lateinit var list: List<TvResult>
        try {
            val listMovie:List<TvResult> = tvLocalDataSource.getTvFromDb()
             if(listMovie.size>0){
                list = listMovie
            }else{
                 list = getTVFromAPI()
                 tvLocalDataSource.saveTvFromDb(list)
             }

        }catch (e:Exception){

        }

        return list
    }

    suspend fun getTvFromCache():List<TvResult>{
        lateinit var list: List<TvResult>
        try {
            val listMovie:List<TvResult> = tvCacheDataSource.getTvFromCache()
            if(listMovie.size>0){
                list = listMovie
            }else{
                list = getTvFromDb()
                tvCacheDataSource.setTvDataCache(list)
            }

        }catch (e:Exception){

        }

        return list
    }


}
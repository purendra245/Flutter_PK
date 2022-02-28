package com.example.demoproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PopularTvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShow(list: List<com.example.demoproject.data.model.tv.TvResult>)

    @Query("Delete from popular_tv_show")
    suspend fun deleteTvShow()

    @Query("Select * from popular_tv_show")
    suspend fun getTvShow(): List<com.example.demoproject.data.model.tv.TvResult>


}
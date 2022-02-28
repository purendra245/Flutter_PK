package com.example.demoproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PopularPeopleDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularPeople(list: List<com.example.demoproject.data.model.people.People>)

    @Query("Delete from popular_artists")
    suspend fun deletePopularPeople()

    @Query("Select * from popular_artists")
    suspend fun getPopularPeople():List<com.example.demoproject.data.model.people.People>


}
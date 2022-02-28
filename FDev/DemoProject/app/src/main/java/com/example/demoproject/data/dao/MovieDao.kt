package com.example.demoproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demoproject.data.model.movie.Movie

@Dao
interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(list: List<Movie>)

    @Query("Delete from popular_movie")
    suspend fun deleteMovie()

    @Query("Select * from popular_movie")
    suspend fun getMovie(): List<Movie>


}
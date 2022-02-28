package com.example.demoproject.data.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.example.demoproject.data.dao.MovieDao
import com.example.demoproject.data.dao.PopularPeopleDao
import com.example.demoproject.data.dao.PopularTvDao
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult

@Database(entities = [TvResult::class,People::class,Movie::class],version = 1)
abstract class TMDBDatabase:RoomDatabase(){

    abstract  fun getMovieDao():MovieDao
    abstract  fun getPopularPeopleDao():PopularPeopleDao
    abstract  fun getPopularTvDao():PopularTvDao
}
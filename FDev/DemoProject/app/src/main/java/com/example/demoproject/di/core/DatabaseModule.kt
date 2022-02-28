package com.example.demoproject.di.core

import android.content.Context
import androidx.room.Room
import com.example.demoproject.data.dao.MovieDao
import com.example.demoproject.data.dao.PopularPeopleDao
import com.example.demoproject.data.dao.PopularTvDao
import com.example.demoproject.data.database.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context:Context): TMDBDatabase {
        return  Room.databaseBuilder(context,
            TMDBDatabase::class.java,"tmdbclient").build()
    }
    @Singleton
    @Provides
    fun getMovieDAO(tmdbDatabase: TMDBDatabase):MovieDao{
        return  tmdbDatabase.getMovieDao()
    }
    @Singleton
    @Provides
    fun getPeopleDAO(tmdbDatabase: TMDBDatabase): PopularPeopleDao {
        return  tmdbDatabase.getPopularPeopleDao()
    }
    @Singleton
    @Provides
    fun getTvDAO(tmdbDatabase: TMDBDatabase): PopularTvDao {
        return  tmdbDatabase.getPopularTvDao()
    }


}
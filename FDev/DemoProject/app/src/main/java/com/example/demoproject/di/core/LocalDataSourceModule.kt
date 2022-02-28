package com.example.demoproject.di.core

import androidx.core.view.ViewCompat
import com.example.demoproject.data.api.datasource.TMDBService
import com.example.demoproject.data.dao.MovieDao
import com.example.demoproject.data.dao.PopularPeopleDao
import com.example.demoproject.data.dao.PopularTvDao
import com.example.demoproject.data.repository.movie.datasource.*
import com.example.demoproject.data.repository.movie.datasourceimpl.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun getMovieLocalDataSource(  movieDao: MovieDao): MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun getPeopleLocalDataSource(peopleDao: PopularPeopleDao): ArtistLocalDataSource{
        return ArtistLocalDataSourceImpl(peopleDao)
    }

    @Singleton
    @Provides
    fun getTvLocalDataSource(tvDao: PopularTvDao): TvLocalDataSource{
        return TvLocalDataSourceImpl(tvDao)
    }
}
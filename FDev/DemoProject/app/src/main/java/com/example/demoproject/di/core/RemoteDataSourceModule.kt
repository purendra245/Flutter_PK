package com.example.demoproject.di.core

import androidx.core.view.ViewCompat
import com.example.demoproject.data.api.datasource.TMDBService
import com.example.demoproject.data.repository.movie.datasource.ArtistRemoteDataSource
import com.example.demoproject.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.demoproject.data.repository.movie.datasource.TvRemoteDataSource
import com.example.demoproject.data.repository.movie.datasourceimpl.ArtistRemoteDataSourceImpl
import com.example.demoproject.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.example.demoproject.data.repository.movie.datasourceimpl.TvRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataSourceModule(val apiKey:String) {

    @Singleton
    @Provides
    fun getMovieDataSource( tmdb: TMDBService): MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(tmdb,apiKey)
    }

    @Singleton
    @Provides
    fun getPeopleDataSource( tmdb: TMDBService): ArtistRemoteDataSource{
        return ArtistRemoteDataSourceImpl(tmdb,apiKey)
    }

    @Singleton
    @Provides
    fun getTvDataSource( tmdb: TMDBService): TvRemoteDataSource{
        return TvRemoteDataSourceImpl(tmdb,apiKey)
    }
}
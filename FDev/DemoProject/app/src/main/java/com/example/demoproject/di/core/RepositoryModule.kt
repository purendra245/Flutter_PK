package com.example.demoproject.di.core

import com.example.demoproject.data.repository.ArtistRepositoryImpl
import com.example.demoproject.data.repository.MovieRepositoryImpl
import com.example.demoproject.data.repository.TvRepositoryImpl
import com.example.demoproject.data.repository.movie.datasource.*
import com.example.demoproject.domain.repository.MovieRepository
import com.example.demoproject.domain.repository.PeopleRepository
import com.example.demoproject.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun getMovieRepository(
         movieRemoteDataSource: MovieRemoteDataSource
        ,movieLocalDataSource: MovieLocalDataSource
        , movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }


    @Provides
    @Singleton
    fun getArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource
        ,artistLocalDataSource: ArtistLocalDataSource
        ,artistCacheDataSource: ArtistCacheDataSource
    ): PeopleRepository {
        return ArtistRepositoryImpl(
            artistRemoteDataSource,
            artistLocalDataSource,
            artistCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun getTvRepository(
        tvRemoteDataSource: TvRemoteDataSource
        ,tvLocalDataSource: TvLocalDataSource
        , tvCacheDataSource: TvCacheDataSource
    ): TvShowRepository {
        return TvRepositoryImpl(
            tvRemoteDataSource,
            tvLocalDataSource,
            tvCacheDataSource
        )
    }
}
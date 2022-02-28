package com.example.demoproject.di.core

import androidx.core.view.ViewCompat
import com.example.demoproject.data.api.datasource.TMDBService
import com.example.demoproject.data.repository.movie.datasource.*
import com.example.demoproject.data.repository.movie.datasourceimpl.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataSourceModule {

    @Singleton
    @Provides
    fun getMovieCacheDataSource(): MovieCacheDataSource{
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun getPeopleCacheDataSource( ): ArtistCacheDataSource{
        return ArtistCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun getTvDCacheDataSource( ): TvCacheDataSource{
        return TvCacheDataSourceImpl()
    }
}
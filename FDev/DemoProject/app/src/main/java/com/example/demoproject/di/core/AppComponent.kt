package com.example.demoproject.di.core

import com.example.demoproject.di.movie.MovieSubComponent
import com.example.demoproject.di.people.ArtistSubComponent
import com.example.demoproject.di.tv.TvSubComponent
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ApiInstance::class,
    AppModule::class,
    CacheDataSourceModule::class,
    DatabaseModule::class,
    LocalDataSourceModule::class,
    RemoteDataSourceModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface AppComponent {

    fun getMovieSubComponent() : MovieSubComponent.Factory
    fun getTvSubComponent(): TvSubComponent.Factory
    fun getPeopleSubComponent(): ArtistSubComponent.Factory
}
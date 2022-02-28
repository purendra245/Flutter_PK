package com.example.demoproject.di.core

import com.example.demoproject.domain.repository.MovieRepository
import com.example.demoproject.domain.repository.PeopleRepository
import com.example.demoproject.domain.repository.TvShowRepository
import com.example.demoproject.domain.useCase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getMovieUseCase(repository: MovieRepository): GetMovieUseCase {
        return GetMovieUseCase(repository)
    }

    @Provides
    fun updateMovieUseCase(repository: MovieRepository): UpdateMovieUseCase {
        return UpdateMovieUseCase(
            repository
        )
    }



    @Provides
    fun getTvUseCase(repository: TvShowRepository): GetTvShowUseCase {
        return GetTvShowUseCase(repository)
    }

    @Provides
    fun updateTvUseCase(repository: TvShowRepository): UpdateTvShowUseCase {
        return UpdateTvShowUseCase(
            repository
        )
    }



    @Provides
    fun getPeopleUseCase(repository: PeopleRepository): GetPeopleUseCase {
        return GetPeopleUseCase(repository)
    }

    @Provides
    fun updatePeopleUseCase(repository: PeopleRepository): UpdatePeopleUseCase {
        return UpdatePeopleUseCase(
            repository
        )
    }


}